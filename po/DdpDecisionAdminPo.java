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
public class DdpDecisionAdminPo implements Serializable {
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

    /**
     * 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 工号
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 工号
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 姓名
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * 姓名
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 创建人
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 创建人
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 创建时间
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * 创建时间
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * 上一次更新人
     */
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * 上一次更新人
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * 上一次更新时间
     */
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * 上一次更新时间
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 是否可用，用来做逻辑删除 1:是 0否
     */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    /**
     * 是否可用，用来做逻辑删除 1:是 0否
     */
    public Integer getEnabled() {
        return enabled;
    }

    /**
     * 版本号，用来做乐观锁
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 版本号，用来做乐观锁
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 禁用时间
     */
    public void setDisabledDate(Date disabledDate) {
        this.disabledDate = disabledDate;
    }

    /**
     * 禁用时间
     */
    public Date getDisabledDate() {
        return disabledDate;
    }
}
