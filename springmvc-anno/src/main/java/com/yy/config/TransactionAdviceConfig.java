package com.yy.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Aspect
public class TransactionAdviceConfig {
    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.yy.service..*.*(..))";

    @Bean
    public TransactionInterceptor transactionInterceptor(PlatformTransactionManager platformTransactionManager) {
        DefaultTransactionAttribute txAttrRequired = new DefaultTransactionAttribute();
        txAttrRequired.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        DefaultTransactionAttribute txAttrRequiredReadonly = new DefaultTransactionAttribute();
        txAttrRequiredReadonly.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttrRequiredReadonly.setReadOnly(true);

        NameMatchTransactionAttributeSource transactionAttributeSource = new NameMatchTransactionAttributeSource();

        // read
        transactionAttributeSource.addTransactionalMethod("query*", txAttrRequiredReadonly);
        transactionAttributeSource.addTransactionalMethod("get*", txAttrRequiredReadonly);
        transactionAttributeSource.addTransactionalMethod("select*", txAttrRequiredReadonly);
        transactionAttributeSource.addTransactionalMethod("find*", txAttrRequiredReadonly);

        // write
        transactionAttributeSource.addTransactionalMethod("save*", txAttrRequired);
        transactionAttributeSource.addTransactionalMethod("insert*", txAttrRequired);
        transactionAttributeSource.addTransactionalMethod("add*", txAttrRequired);
        transactionAttributeSource.addTransactionalMethod("create*", txAttrRequired);
        transactionAttributeSource.addTransactionalMethod("delete*", txAttrRequired);
        transactionAttributeSource.addTransactionalMethod("update*", txAttrRequired);
        transactionAttributeSource.addTransactionalMethod("remove*", txAttrRequired);
        transactionAttributeSource.addTransactionalMethod("modify*", txAttrRequired);

        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        transactionInterceptor.setTransactionManager(platformTransactionManager);
        transactionInterceptor.setTransactionAttributeSource(transactionAttributeSource);
        return transactionInterceptor;
    }

    @Bean
    public Advisor transactionAdviceAdvisor(TransactionInterceptor transactionInterceptor) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        // 定义切面
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, transactionInterceptor);
    }
}
