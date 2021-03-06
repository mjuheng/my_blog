package com.heng.entity;

import java.util.Date;

public class Article {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.aid
     *
     * @mbg.generated Sun Sep 03 20:13:49 CST 2017
     */
    private Integer aid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.uid
     *
     * @mbg.generated Sun Sep 03 20:13:49 CST 2017
     */
    private Integer uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.title
     *
     * @mbg.generated Sun Sep 03 20:13:49 CST 2017
     */
    private String title;


    private String markdown;

    private String html;


    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.gmt_post
     *
     * @mbg.generated Sun Sep 03 20:13:49 CST 2017
     */
    private Date gmtPost;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.view
     *
     * @mbg.generated Sun Sep 03 20:13:49 CST 2017
     */
    private Integer view;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.aid
     *
     * @return the value of article.aid
     *
     * @mbg.generated Sun Sep 03 20:13:49 CST 2017
     */
    public Integer getAid() {
        return aid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.aid
     *
     * @param aid the value for article.aid
     *
     * @mbg.generated Sun Sep 03 20:13:49 CST 2017
     */
    public void setAid(Integer aid) {
        this.aid = aid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.uid
     *
     * @return the value of article.uid
     *
     * @mbg.generated Sun Sep 03 20:13:49 CST 2017
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.uid
     *
     * @param uid the value for article.uid
     *
     * @mbg.generated Sun Sep 03 20:13:49 CST 2017
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.title
     *
     * @return the value of article.title
     *
     * @mbg.generated Sun Sep 03 20:13:49 CST 2017
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.title
     *
     * @param title the value for article.title
     *
     * @mbg.generated Sun Sep 03 20:13:49 CST 2017
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.gmt_post
     *
     * @return the value of article.gmt_post
     *
     * @mbg.generated Sun Sep 03 20:13:49 CST 2017
     */
    public Date getGmtPost() {
        return gmtPost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.gmt_post
     *
     * @param gmtPost the value for article.gmt_post
     *
     * @mbg.generated Sun Sep 03 20:13:49 CST 2017
     */
    public void setGmtPost(Date gmtPost) {
        this.gmtPost = gmtPost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.view
     *
     * @return the value of article.view
     *
     * @mbg.generated Sun Sep 03 20:13:49 CST 2017
     */
    public Integer getView() {
        return view;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.view
     *
     * @param view the value for article.view
     *
     * @mbg.generated Sun Sep 03 20:13:49 CST 2017
     */
    public void setView(Integer view) {
        this.view = view;
    }
}