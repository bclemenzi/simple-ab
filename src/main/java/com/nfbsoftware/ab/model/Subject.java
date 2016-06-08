package com.nfbsoftware.ab.model;

import java.io.Serializable;

/**
 * 
 * @author brendanclemenzi
 */
public class Subject implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private String m_descr;
    private String m_code;

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
