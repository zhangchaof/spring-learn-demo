package com.example.spring.learn.demo.mybatis.entity;

import lombok.ToString;

import java.util.Date;
@ToString
public class Employee {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.lastName
     *
     * @mbg.generated
     */
    private String lastName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.email
     *
     * @mbg.generated
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.gender
     *
     * @mbg.generated
     */
    private Boolean gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.depart_id
     *
     * @mbg.generated
     */
    private Integer departId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.is_delete
     *
     * @mbg.generated
     */
    private Byte isDelete;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.id
     *
     * @return the value of employee.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.id
     *
     * @param id the value for employee.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.lastName
     *
     * @return the value of employee.lastName
     *
     * @mbg.generated
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.lastName
     *
     * @param lastName the value for employee.lastName
     *
     * @mbg.generated
     */
    public void setLastName(String lastname) {
        this.lastName = lastname == null ? null : lastname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.email
     *
     * @return the value of employee.email
     *
     * @mbg.generated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.email
     *
     * @param email the value for employee.email
     *
     * @mbg.generated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.gender
     *
     * @return the value of employee.gender
     *
     * @mbg.generated
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.gender
     *
     * @param gender the value for employee.gender
     *
     * @mbg.generated
     */
    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.depart_id
     *
     * @return the value of employee.depart_id
     *
     * @mbg.generated
     */
    public Integer getDepartId() {
        return departId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.depart_id
     *
     * @param departId the value for employee.depart_id
     *
     * @mbg.generated
     */
    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.is_delete
     *
     * @return the value of employee.is_delete
     *
     * @mbg.generated
     */
    public Byte getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.is_delete
     *
     * @param isDelete the value for employee.is_delete
     *
     * @mbg.generated
     */
    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.create_time
     *
     * @return the value of employee.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.create_time
     *
     * @param createTime the value for employee.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee.update_time
     *
     * @return the value of employee.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee.update_time
     *
     * @param updateTime the value for employee.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}