package com.nfbsoftware.ab.model;

/**
 * 
 * @author brendanclemenzi
 */
public class SubjectDocument
{
    private String m_guid;
    private String m_descr;

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
}
