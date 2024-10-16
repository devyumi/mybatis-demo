package com.example.mybatis_demo.repository;

import com.example.mybatis_demo.domain.Product;
import com.example.mybatis_demo.domain.QMember;
import com.example.mybatis_demo.domain.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Product> findAll() {
        QProduct product = QProduct.product;

        return queryFactory.selectFrom(product)
                .innerJoin(product.member, QMember.member)
                .fetchJoin()
                .orderBy(product.id.desc())
                .fetch();
    }

//    @Override
//    public ResponseDto findAll(RequestDto requestDto) {
//        return null;
//    }
}
