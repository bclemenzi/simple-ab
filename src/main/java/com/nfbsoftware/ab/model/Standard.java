package com.nfbsoftware.ab.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author brendanclemenzi
 */
public class Standard
{
    private String m_id;
    private String m_status;
    private String m_placeholder;
    private String m_guid;
    private String m_descr;
    private String m_adopt_year;
    private String m_self;
    private String m_date_modified;
    private String m_deepest;
    private String m_number;
    private String m_version;
    private String m_label;
    private String m_stem;
    private String m_seq;
    private String m_extended_descr;

    private String m_authorityGuid;
    private String m_authorityCode;
    private String m_authorityDescr;
    private String m_documentGuid;
    private String m_documentTitle;
    private String m_subjectCode;
    private String m_subjectDescr;
    private String m_subjectDocGuid;
    private String m_subjectDocDescr;
    private String m_courseGuid;
    private String m_courseDescr;
    
    private List<Standard> m_standards = new ArrayList<Standard>();

    public String getStatus()
    {
        return m_status;
    }
    public void setStatus(String status)
    {
        m_status = status;
    }
    
    public String getPlaceholder()
    {
        return m_placeholder;
    }
    public void setPlaceholder(String placeholder)
    {
        m_placeholder = placeholder;
    }
    
    public String getId()
    {
        return m_guid;
    }
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
    
    public String getAdopt_year()
    {
        return m_adopt_year;
    }
    public void setAdopt_year(String adopt_year)
    {
        m_adopt_year = adopt_year;
    }
    
    public String getSelf()
    {
        return m_self;
    }
    public void setSelf(String self)
    {
        m_self = self;
    }
    
    public String getDate_modified()
    {
        return m_date_modified;
    }
    public void setDate_modified(String date_modified)
    {
        m_date_modified = date_modified;
    }
    
    public String getDeepest()
    {
        return m_deepest;
    }
    public void setDeepest(String deepest)
    {
        m_deepest = deepest;
    }
    
    public String getNumber()
    {
        return m_number;
    }
    public void setNumber(String number)
    {
        m_number = number;
    }
    
    public String getVersion()
    {
        return m_version;
    }
    public void setVersion(String version)
    {
        m_version = version;
    }
    
    public String getLabel()
    {
        return m_label;
    }
    public void setLabel(String label)
    {
        m_label = label;
    }
    
    public String getAuthorityGuid()
    {
        return m_authorityGuid;
    }
    public void setAuthorityGuid(String authorityGuid)
    {
        m_authorityGuid = authorityGuid;
    }
    
    public String getAuthorityCode()
    {
        return m_authorityCode;
    }
    public void setAuthorityCode(String authorityCode)
    {
        m_authorityCode = authorityCode;
    }
    
    public String getAuthorityDescr()
    {
        return m_authorityDescr;
    }
    public void setAuthorityDescr(String authorityDescr)
    {
        m_authorityDescr = authorityDescr;
    }
    
    public String getDocumentGuid()
    {
        return m_documentGuid;
    }
    public void setDocumentGuid(String documentGuid)
    {
        m_documentGuid = documentGuid;
    }
    
    public String getDocumentTitle()
    {
        return m_documentTitle;
    }
    public void setDocumentTitle(String documentTitle)
    {
        m_documentTitle = documentTitle;
    }
    
    public String getSubjectCode()
    {
        return m_subjectCode;
    }
    public void setSubjectCode(String subjectCode)
    {
        m_subjectCode= subjectCode;
    }
    
    public String getSubjectDescr()
    {
        return m_subjectDescr;
    }
    public void setSubjectDescr(String subjectDescr)
    {
        m_subjectDescr = subjectDescr;
    }
    
    public String getSubjectDocGuid()
    {
        return m_subjectDocGuid;
    }
    public void setSubjectDocGuid(String subjectDocGuid)
    {
        m_subjectDocGuid = subjectDocGuid;
    }
    
    public String getSubjectDocDescr()
    {
        return m_subjectDocDescr;
    }
    public void setSubjectDocDescr(String subjectDocDescr)
    {
        m_subjectDocDescr = subjectDocDescr;
    }
    
    public String getCourseGuid()
    {
        return m_courseGuid;
    }
    public void setCourseGuid(String courseGuid)
    {
        m_courseGuid = courseGuid;
    }
    
    public String getCourseDescr()
    {
        return m_courseDescr;
    }
    public void setCourseDescr(String courseDescr)
    {
        m_courseDescr = courseDescr;
    }
    
    public String getStem()
    {
        return m_stem;
    }
    public void setStem(String stem)
    {
        m_stem = stem;
    }
    
    public String getSeq()
    {
        return m_seq;
    }
    public void setSeq(String seq)
    {
        m_seq = seq;
    }
    
    public String getExtended_descr()
    {
        return m_extended_descr;
    }
    public void setExtended_descr(String extended_descr)
    {
        m_extended_descr = extended_descr;
    }
    
    public List<Standard> getStandards()
    {
        return m_standards;
    }
    public void setStandards(List<Standard> standards)
    {
        m_standards = standards;
    }
}
