package com.nfbsoftware.ab.model;

import java.io.Serializable;

/**
 * 
 * @author brendanclemenzi
 */
public class Grade implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private String m_code;
    private String m_lo;
    private String m_hi;
    private String m_descr;
    
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
    
    public String getLo()
    {
        return m_lo;
    }
    public void setLo(String lo)
    {
        m_lo = lo;
    }
    
    public String getHi()
    {
        return m_hi;
    }
    public void setHi(String hi)
    {
        m_hi = hi;
    }
}
