package com.lind.core.entity;

/**
 * 软删除类型.
 */
public class Deleteable {
  boolean deleted;

  public boolean isDeleted() {

    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }
}
