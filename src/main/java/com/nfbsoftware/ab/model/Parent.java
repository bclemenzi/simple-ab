package com.nfbsoftware.ab.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author brendanclemenzi
 */
public class Parent implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String m_guid;
    private String m_descr;
    private String m_number;
    private String m_stem;
    private String m_label;
    private String m_status;
    private String m_version;
    private String m_placeholder;
    private String m_date_modified;
    private String m_extended_descr;
    private String m_deepest;
    private String m_seq;

    private int m_level;

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

    public String getNumber()
    {
        return m_number;
    }
    public void setNumber(String number)
    {
        m_number = number;
    }

    public String getStem()
    {
        return m_stem;
    }
    public void setStem(String stem)
    {
        m_stem = stem;
    }

    public String getLabel()
    {
        return m_label;
    }
    public void setLabel(String label)
    {
        m_label = label;
    }

    public String getStatus()
    {
        return m_status;
    }
    public void setStatus(String status)
    {
        m_status = status;
    }

    public String getVersion()
    {
        return m_version;
    }
    public void setVersion(String version)
    {
        m_version = version;
    }

    public String getPlaceholder()
    {
        return m_placeholder;
    }
    public void setPlaceholder(String placeholder)
    {
        m_placeholder = placeholder;
    }

    public String getDate_modified()
    {
        return m_date_modified;
    }
    public void setDate_modified(String date_modified)
    {
        m_date_modified = date_modified;
    }

    public String getExtended_descr()
    {
        return m_extended_descr;
    }
    public void setExtended_descr(String extended_descr)
    {
        m_extended_descr = extended_descr;
    }

    public String getDeepest()
    {
        return m_deepest;
    }
    public void setDeepest(String deepest)
    {
        m_deepest = deepest;
    }

    public String getSeq()
    {
        return m_seq;
    }
    public void setSeq(String seq)
    {
        m_seq = seq;
    }

    public int getLevel()
    {
        return m_level;
    }
    public void setLevel(int level)
    {
        m_level = level;
    }
}
