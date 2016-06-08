package com.nfbsoftware.ab.model;

import java.io.Serializable;



/**
 * 
 * @author brendanclemenzi
 */
public class AbResource implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private AbData m_data;

    public AbData getData()
    {
        return m_data;
    }
    public void setData(AbData data)
    {
        m_data = data;
    }
}
