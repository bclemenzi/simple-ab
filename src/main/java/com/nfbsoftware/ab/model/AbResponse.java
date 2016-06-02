package com.nfbsoftware.ab.model;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author brendanclemenzi
 */
public class AbResponse
{
    private String m_type;
    private String m_first;
    private String m_previous;
    private String m_next;
    private String m_last;
    
    private int m_count;
    private int m_offset;
    private int m_limit;
    private int m_took;
    
    private Status m_status;
    
    private List<AbResource> m_resources = new ArrayList<AbResource>();
    
    public String getType()
    {
        return m_type;
    }
    public void setType(String type)
    {
        m_type = type;
    }
    
    public String getFirst()
    {
        return m_first;
    }
    public void setFirst(String first)
    {
        m_first = first;
    }
    
    public String getPrevious()
    {
        return m_previous;
    }
    public void setPrevious(String previous)
    {
        m_previous = previous;
    }
    
    public String getNext()
    {
        return m_next;
    }
    public void setNext(String next)
    {
        m_next = next;
    }
    
    public String getLast()
    {
        return m_last;
    }
    public void setLast(String last)
    {
        m_last = last;
    }
    
    public int getCount()
    {
        return m_count;
    }
    public void setCount(int count)
    {
        m_count = count;
    }
    
    public int getOffset()
    {
        return m_offset;
    }
    public void setOffset(int offset)
    {
        m_offset = offset;
    }
    
    public int getLimit()
    {
        return m_limit;
    }
    public void setLimit(int limit)
    {
        m_limit = limit;
    }
    
    public int getTook()
    {
        return m_took;
    }
    public void setTook(int took)
    {
        m_took = took;
    }
    
    public Status getStatus()
    {
        return m_status;
    }
    public void setStatus(Status status)
    {
        m_status = status;
    }
    
    public List<AbResource> getResources()
    {
        return m_resources;
    }
    public void setResources(List<AbResource> resources)
    {
        m_resources = resources;
    }
}
