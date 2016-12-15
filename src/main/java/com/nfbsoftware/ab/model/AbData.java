package com.nfbsoftware.ab.model;

import java.io.Serializable;

import com.nfbsoftware.util.StringUtil;

/**
 * 
 * @author brendanclemenzi
 */
public class AbData implements Serializable
{
    private static final long serialVersionUID = 1L;
    
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
    
    private int m_level;
    
    private Authority m_authority;
    private Grade m_grade;
    private Course m_course;
    private Document m_document;
    private Subject m_subject;
    private SubjectDocument m_subject_doc;
    private Parent m_parent;

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
    
    public int getLevel()
    {
        return m_level;
    }
    public void setLevel(int level)
    {
        m_level = level;
    }
    
    public Authority getAuthority()
    {
        return m_authority;
    }
    public void setAuthority(Authority authority)
    {
        m_authority = authority;
    }
    
    public Grade getGrade()
    {
        return m_grade;
    }
    public void setGrade(Grade grade)
    {
        m_grade = grade;
    }
    
    public Course getCourse()
    {
        return m_course;
    }
    public void setCourse(Course course)
    {
        m_course = course;
    }
    
    public Document getDocument()
    {
        return m_document;
    }
    public void setDocument(Document document)
    {
        m_document = document;
    }
    
    public Subject getSubject()
    {
        return m_subject;
    }
    public void setSubject(Subject subject)
    {
        m_subject = subject;
    }
    
    public SubjectDocument getSubject_doc()
    {
        return m_subject_doc;
    }
    public void setSubject_doc(SubjectDocument subject_doc)
    {
        m_subject_doc = subject_doc;
    }
    
    public Parent getParent()
    {
        return m_parent;
    }
    public void setParent(Parent parent)
    {
        m_parent = parent;
    }
    
    public String getDisplayName()
    {
        String displayName = "";
        
        if(StringUtil.isNullOrEmpty(getNumber()))
        {
            displayName = StringUtil.emptyIfNull(getDescr());
        }
        else
        {
            displayName = StringUtil.emptyIfNull(getNumber()) + " " + StringUtil.emptyIfNull(getDescr());
        }
        
        return displayName;
    }
}
