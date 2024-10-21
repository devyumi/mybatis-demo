package com.example.mybatis_demo.repository;

import com.example.mybatis_demo.domain.Product;
import com.example.mybatis_demo.domain.QMember;
import com.example.mybatis_demo.domain.QProduct;
import com.example.mybatis_demo.dto.RequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final JPAQueryFactory queryFactory;
    private final QProduct product = QProduct.product;

    @Override
    public List<Product> findAll(RequestDto requestDto) {

        //기본
        JPAQuery<Product> result = queryFactory.selectFrom(product)
                .innerJoin(product.member, QMember.member);

        //동적쿼리
        processWhere(result, requestDto);
        return result.orderBy(product.id.asc())
                .offset(requestDto.getOffset())
                .limit(requestDto.getSize())
                .fetchJoin().fetch();
    }

    @Override
    public Integer count(RequestDto requestDto) {

        //기본
        JPAQuery<Long> result = queryFactory.select(product.count())
                .from(product)
                .innerJoin(product.member, QMember.member);

        //동적쿼리
        processWhere(result, requestDto);
        return result.fetchFirst().intValue();
    }

    private void processWhere(JPAQuery<?> result, RequestDto requestDto) {
        if (requestDto.getKeyword() != null) {

            switch (requestDto.getType()) {
                case "all" -> {
                    BooleanBuilder booleanBuilder = new BooleanBuilder();
                    booleanBuilder.or(product.name.contains(requestDto.getKeyword()));
                    booleanBuilder.or(product.manufacturer.contains(requestDto.getKeyword()));
                    result.where(booleanBuilder);
                }

                case "name" -> result.where(product.name.contains(requestDto.getKeyword()));

                case "manufacturer" -> result.where(product.manufacturer.contains(requestDto.getKeyword()));

                case "price" -> result.where(product.price.between(requestDto.getPriceFrom(), requestDto.getPriceTo()));
            }
        }
    }
}
