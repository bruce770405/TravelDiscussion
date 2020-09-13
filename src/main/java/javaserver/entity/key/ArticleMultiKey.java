package javaserver.entity.key;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author BruceHsu
 * @Param Id
 * @Param stepId
 * <p>
 * 多key連結
 */
@Getter
@Setter
@EqualsAndHashCode
public class ArticleMultiKey implements Serializable {

    private Long id;
    private Long stepId;

}
