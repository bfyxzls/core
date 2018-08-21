package com.lind.core.entity;

import java.util.Date;

public class Auditable {
  Date auditTime;
  String auditName;

  public Date getAuditTime() {

    return auditTime;
  }

  public void setAuditTime(Date auditTime) {
    this.auditTime = auditTime;
  }

  public String getAuditName() {
    return auditName;
  }

  public void setAuditName(String auditName) {
    this.auditName = auditName;
  }
}
