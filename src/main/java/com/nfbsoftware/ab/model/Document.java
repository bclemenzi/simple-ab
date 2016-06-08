package com.nfbsoftware.ab.model;

import java.io.Serializable;

/**
 * 
 * @author brendanclemenzi
 */
public class Document implements Serializable
{
    private static final long serialVersionUID = 1L;
    
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
