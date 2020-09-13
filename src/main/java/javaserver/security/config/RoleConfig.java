//package javaserver.security.config;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.access.AccessDecisionVoter;
//import org.springframework.security.access.annotation.Jsr250Voter;
//import org.springframework.security.access.expression.method.ExpressionBasedPreInvocationAdvice;
//import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
//import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
//import org.springframework.security.access.prepost.PreInvocationAuthorizationAdviceVoter;
//import org.springframework.security.access.vote.AffirmativeBased;
//import org.springframework.security.access.vote.AuthenticatedVoter;
//import org.springframework.security.access.vote.RoleHierarchyVoter;
//import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
//
///**
// * 
// * @author BruceHsu 2018/03/19
// * 定義role的權限關係
// */
//@Configuration
//public class RoleConfig extends GlobalMethodSecurityConfiguration{
//
//    @Override
//    protected AccessDecisionManager accessDecisionManager() {
//        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<AccessDecisionVoter<? extends Object>>();
//        ExpressionBasedPreInvocationAdvice expressionAdvice = new ExpressionBasedPreInvocationAdvice();
//        expressionAdvice.setExpressionHandler(getExpressionHandler());
////        if (prePostEnabled()) {
//            decisionVoters
//                    .add(new PreInvocationAuthorizationAdviceVoter(expressionAdvice));
////        }
////        if (jsr250Enabled()) {
//            decisionVoters.add(new Jsr250Voter());
////        }
////        decisionVoters.add(new RoleVoter());
//        decisionVoters.add(roleHierarchyVoter());
//        decisionVoters.add(new AuthenticatedVoter());
//
//        return new AffirmativeBased(decisionVoters);
//    }
//
//
//    @Bean
//    public RoleHierarchyVoter roleHierarchyVoter() {
//        return new RoleHierarchyVoter(roleHierarchy());
//    }
//
//    @Bean
//    public RoleHierarchy roleHierarchy(){
//        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
//        roleHierarchy.setHierarchy(
//                "ROLE_ADMIN > ROLE_USER\n"+
//                " ROLE_USER > ROLE_ANONYMOUS\n"
//        );
//        return roleHierarchy;
//    }
//}