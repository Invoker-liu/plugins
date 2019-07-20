import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统管理员 系统管理员
 *
 * @author itar
 * @mail wuhandzy@gmail.com
 * @date 2019-07-20 01:51:11
 * @since jdk1.8
 */
@Setter
@Getter
public class DdpDecisionAdminBo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 工号
     */
    private String userName;
    /**
     * 姓名
     */
    private String displayName;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdDate;
    /**
     * 上一次更新人
     */
    private String lastModifiedBy;
    /**
     * 上一次更新时间
     */
    private Date lastModifiedDate;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 是否可用，用来做逻辑删除 1:是 0否
     */
    private Integer enabled;
    /**
     * 版本号，用来做乐观锁
     */
    private Integer version;
    /**
     * 禁用时间
     */
    private Date disabledDate;
}
