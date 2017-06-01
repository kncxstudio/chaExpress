package wang.junqin.chaexpress.greenDAO.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by KN on 2017/5/31.
 */

@Entity
public class ExpInfoEntity {
    @Id(autoincrement = true)
    Long id;

    @NotNull
    String expNum;

    @NotNull
    String expInfo;

    @NotNull
    String status;
    
    String remark;

    @Generated(hash = 452777304)
    public ExpInfoEntity(Long id, @NotNull String expNum, @NotNull String expInfo,
            @NotNull String status, String remark) {
        this.id = id;
        this.expNum = expNum;
        this.expInfo = expInfo;
        this.status = status;
        this.remark = remark;
    }

    @Generated(hash = 219973270)
    public ExpInfoEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpNum() {
        return this.expNum;
    }

    public void setExpNum(String expNum) {
        this.expNum = expNum;
    }

    public String getExpInfo() {
        return this.expInfo;
    }

    public void setExpInfo(String expInfo) {
        this.expInfo = expInfo;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
