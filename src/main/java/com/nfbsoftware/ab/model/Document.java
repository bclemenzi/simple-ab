package com.nfbsoftware.ab.model;

/**
 * 
 * @author brendanclemenzi
 */
public class Document
{
    private String m_guid;
    private String m_title;

    public String getGuid()
    {
        return m_guid;
    }
    public void setGuid(String guid)
    {
        m_guid = guid;
    }
    
    public String getTitle()
    {
        return m_title;
    }
    public void setTitle(String title)
    {
        m_title = title;
    }
}
