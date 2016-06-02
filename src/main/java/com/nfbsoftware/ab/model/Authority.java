package com.nfbsoftware.ab.model;

/**
 * 
 * @author brendanclemenzi
 */
public class Authority
{
    private String m_guid;
    private String m_descr;
    private String m_code;
    
    public String getGuid()
    {
        return m_guid;
    }
    public void setGuid(String guid)
    {
        m_guid = guid;
    }

    public String getDescr()
    {
        return m_descr;
    }
    public void setDescr(String descr)
    {
        m_descr = descr;
    }
    
    public String getCode()
    {
        return m_code;
    }
    public void setCode(String code)
    {
        m_code = code;
    }
}
