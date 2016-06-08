package com.nfbsoftware.ab.model;

import java.io.Serializable;



/**
 * 
 * @author brendanclemenzi
 */
public class Status implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private String m_imsg;
    private String m_emsg;
    private String m_category;
    
    private int m_code;
    
    public String getImsg()
    {
        return m_imsg;
    }
    public void setImsg(String imsg)
    {
        m_imsg = imsg;
    }
    
    public String getEmsg()
    {
        return m_emsg;
    }
    public void setEmsg(String emsg)
    {
        m_emsg = emsg;
    }
    
    public String getCategory()
    {
        return m_category;
    }
    public void setCategory(String category)
    {
        m_category = category;
    }
    
    public int getCode()
    {
        return m_code;
    }
    public void setCode(int code)
    {
        m_code = code;
    }
}
