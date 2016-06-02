package com.nfbsoftware.ab;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nfbsoftware.ab.model.AbData;
import com.nfbsoftware.ab.model.AbResource;
import com.nfbsoftware.ab.model.AbResponse;
import com.nfbsoftware.ab.model.Authority;
import com.nfbsoftware.ab.model.Course;
import com.nfbsoftware.ab.model.Document;
import com.nfbsoftware.ab.model.Grade;
import com.nfbsoftware.ab.model.Standard;
import com.nfbsoftware.ab.model.Status;
import com.nfbsoftware.ab.model.Subject;
import com.nfbsoftware.ab.model.SubjectDocument;
import com.nfbsoftware.util.StringUtil;
import com.nfbsoftware.util.WebPost;

import flexjson.JSONDeserializer;

/**
 * This is a Java utility class that is used to communicate with the Academic Benchmarks' RESTful API.
 * 
 * @author brendanclemenzi
 */
public class AcademicBenchmarksClient
{
    private static final Log logger = LogFactory.getLog(AcademicBenchmarksClient.class);
    
    private static final String HASH_ALGORITHM = "HmacSHA256";
    
    private static final String AB_API_URL_V3 = "https://api.academicbenchmarks.com/rest/v3/standards";
    
    private String m_partnerKey;
    private String m_partnerId;
    private String m_userId;
    
    private int m_signatureLifespanMinutes;
    
    /**
     * 
     * @param partnerKey - Academic Benchmarks Partner Key
     * @param partnerId - Academic Benchmarks Partner ID
     * @param userId - A GUID used for signing the API signature
     */
    public AcademicBenchmarksClient(String partnerId, String partnerKey, String userId)
    {
        m_partnerId = partnerId;
        m_partnerKey = partnerKey;
        m_userId = userId;
        
        m_signatureLifespanMinutes = 120;
    }
    
    /**
     * 
     * @param partnerKey - Academic Benchmarks Partner Key
     * @param partnerId - Academic Benchmarks Partner ID
     * @param userId - A GUID used for signing the API signature
     * @param signatureLifespanMinutes - Length of time the signature remains valid
     */
    public AcademicBenchmarksClient(String partnerId, String partnerKey, String userId, int signatureLifespanMinutes)
    {
        m_partnerId = partnerId;
        m_partnerKey = partnerKey;
        m_userId = userId;
        
        m_signatureLifespanMinutes = signatureLifespanMinutes;
    }
    
    /**
     * 
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    public List<Authority> getAuthorities(int offset, int limit) throws Exception
    {
        List<Authority> authorities = new ArrayList<Authority>();
        
        String apiResponse = getApiResponse("authority", "", offset, limit);
        
        JSONDeserializer<AbResponse> js = new JSONDeserializer<AbResponse>();
        AbResponse restApiResponse = js.deserialize(apiResponse, AbResponse.class);
        
        Status apiStatus = restApiResponse.getStatus();
        
        if(apiStatus != null)
        {
            if(apiStatus.getCode() == 200)
            {
                for(AbResource tmpResource : restApiResponse.getResources())
                {
                    AbData tmpData = tmpResource.getData();
                    
                    if(tmpData != null)
                    {
                        Authority tmpAuthority = tmpData.getAuthority();
                        
                        if(tmpAuthority != null)
                        {
                            authorities.add(tmpAuthority);
                        }
                    }
                }
            }
            else
            {
                throw new Exception("Academic Benchmarks Error (" + apiStatus.getCode() + ") " + apiStatus.getCategory() + " - " + apiStatus.getEmsg());
            }
        }
        
        return authorities;
    }
    
    /**
     * 
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    public List<Subject> getSubjects(int offset, int limit) throws Exception
    {
        List<Subject> subjects = new ArrayList<Subject>();
        
        String apiResponse = getApiResponse("subject", "", offset, limit);
        
        JSONDeserializer<AbResponse> js = new JSONDeserializer<AbResponse>();
        AbResponse restApiResponse = js.deserialize(apiResponse, AbResponse.class);
        
        Status apiStatus = restApiResponse.getStatus();
        
        if(apiStatus != null)
        {
            if(apiStatus.getCode() == 200)
            {
                for(AbResource tmpResource : restApiResponse.getResources())
                {
                    AbData tmpData = tmpResource.getData();
                    
                    if(tmpData != null)
                    {
                        Subject tmpSubject = tmpData.getSubject();
                        
                        if(tmpSubject != null)
                        {
                            subjects.add(tmpSubject);
                        }
                    }
                }
            }
            else
            {
                throw new Exception("Academic Benchmarks Error (" + apiStatus.getCode() + ") " + apiStatus.getCategory() + " - " + apiStatus.getEmsg());
            }
        }
        
        return subjects;
    }
    
    /**
     * 
     * @param authorityCode
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    public List<Subject> getSubjectsByAuthority(String authorityCode, int offset, int limit) throws Exception
    {
        List<Subject> subjects = new ArrayList<Subject>();
        
        String queryString = "&authority=" + authorityCode;
        
        String apiResponse = getApiResponse("subject", queryString, offset, limit);
        
        JSONDeserializer<AbResponse> js = new JSONDeserializer<AbResponse>();
        AbResponse restApiResponse = js.deserialize(apiResponse, AbResponse.class);
        
        Status apiStatus = restApiResponse.getStatus();
        
        if(apiStatus != null)
        {
            if(apiStatus.getCode() == 200)
            {
                for(AbResource tmpResource : restApiResponse.getResources())
                {
                    AbData tmpData = tmpResource.getData();
                    
                    if(tmpData != null)
                    {
                        Subject tmpSubject = tmpData.getSubject();
                        
                        if(tmpSubject != null)
                        {
                            subjects.add(tmpSubject);
                        }
                    }
                }
            }
            else
            {
                throw new Exception("Academic Benchmarks Error (" + apiStatus.getCode() + ") " + apiStatus.getCategory() + " - " + apiStatus.getEmsg());
            }
        }
        
        return subjects;
    }
    
    public List<Subject> getSubjectsByAuthorityAndDocument(String authorityCode, String documentGuid, int offset, int limit) throws Exception
    {
        List<Subject> subjects = new ArrayList<Subject>();
        
        String queryString = "&authority=" + authorityCode + "&document=" + documentGuid;
        
        String apiResponse = getApiResponse("subject", queryString, offset, limit);
        
        JSONDeserializer<AbResponse> js = new JSONDeserializer<AbResponse>();
        AbResponse restApiResponse = js.deserialize(apiResponse, AbResponse.class);
        
        Status apiStatus = restApiResponse.getStatus();
        
        if(apiStatus != null)
        {
            if(apiStatus.getCode() == 200)
            {
                for(AbResource tmpResource : restApiResponse.getResources())
                {
                    AbData tmpData = tmpResource.getData();
                    
                    if(tmpData != null)
                    {
                        Subject tmpSubject = tmpData.getSubject();
                        
                        if(tmpSubject != null)
                        {
                            subjects.add(tmpSubject);
                        }
                    }
                }
            }
            else
            {
                throw new Exception("Academic Benchmarks Error (" + apiStatus.getCode() + ") " + apiStatus.getCategory() + " - " + apiStatus.getEmsg());
            }
        }
        
        return subjects;
    }
    
    /**
     * 
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    public List<SubjectDocument> getSubjectDocumentsByAuthorityAndDocument(String authorityCode, String documentGuid, int offset, int limit) throws Exception
    {
        List<SubjectDocument> subjectDocuments = new ArrayList<SubjectDocument>();
        
        String queryString = "&authority=" + authorityCode + "&document=" + documentGuid;
        String apiResponse = getApiResponse("subject_doc", queryString, offset, limit);
        
        JSONDeserializer<AbResponse> js = new JSONDeserializer<AbResponse>();
        AbResponse restApiResponse = js.deserialize(apiResponse, AbResponse.class);
        
        Status apiStatus = restApiResponse.getStatus();
        
        if(apiStatus != null)
        {
            if(apiStatus.getCode() == 200)
            {
                for(AbResource tmpResource : restApiResponse.getResources())
                {
                    AbData tmpData = tmpResource.getData();
                    
                    if(tmpData != null)
                    {
                        SubjectDocument tmpSubjectDocument = tmpData.getSubject_doc();
                        
                        if(tmpSubjectDocument != null)
                        {
                            subjectDocuments.add(tmpSubjectDocument);
                        }
                    }
                }
            }
            else
            {
                throw new Exception("Academic Benchmarks Error (" + apiStatus.getCode() + ") " + apiStatus.getCategory() + " - " + apiStatus.getEmsg());
            }
        }
        
        return subjectDocuments;
    }
    
    /**
     * 
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    public List<Grade> getGrades(int offset, int limit) throws Exception
    {
        List<Grade> grades = new ArrayList<Grade>();
        
        String apiResponse = getApiResponse("grade", "", offset, limit);
        
        JSONDeserializer<AbResponse> js = new JSONDeserializer<AbResponse>();
        AbResponse restApiResponse = js.deserialize(apiResponse, AbResponse.class);
        
        Status apiStatus = restApiResponse.getStatus();
        
        if(apiStatus != null)
        {
            if(apiStatus.getCode() == 200)
            {
                for(AbResource tmpResource : restApiResponse.getResources())
                {
                    AbData tmpData = tmpResource.getData();
                    
                    if(tmpData != null)
                    {
                        Grade tmpGrade = tmpData.getGrade();
                        
                        if(tmpGrade != null)
                        {
                            grades.add(tmpGrade);
                        }
                    }
                }
            }
            else
            {
                throw new Exception("Academic Benchmarks Error (" + apiStatus.getCode() + ") " + apiStatus.getCategory() + " - " + apiStatus.getEmsg());
            }
        }
        
        return grades;
    }
    
    /**
     * 
     * @param authorityCode
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    public List<Grade> getGradesByAuthority(String authorityCode, int offset, int limit) throws Exception
    {
        List<Grade> grades = new ArrayList<Grade>();
        
        String queryString = "&authority=" + authorityCode;
        
        String apiResponse = getApiResponse("grade", queryString, offset, limit);
        
        JSONDeserializer<AbResponse> js = new JSONDeserializer<AbResponse>();
        AbResponse restApiResponse = js.deserialize(apiResponse, AbResponse.class);
        
        Status apiStatus = restApiResponse.getStatus();
        
        if(apiStatus != null)
        {
            if(apiStatus.getCode() == 200)
            {
                for(AbResource tmpResource : restApiResponse.getResources())
                {
                    AbData tmpData = tmpResource.getData();
                    
                    if(tmpData != null)
                    {
                        Grade tmpGrade = tmpData.getGrade();
                        
                        if(tmpGrade != null)
                        {
                            grades.add(tmpGrade);
                        }
                    }
                }
            }
            else
            {
                throw new Exception("Academic Benchmarks Error (" + apiStatus.getCode() + ") " + apiStatus.getCategory() + " - " + apiStatus.getEmsg());
            }
        }
        
        return grades;
    }
    
    /**
     * 
     * @param authorityCode
     * @param subjectCode
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    public List<Grade> getGradesByAuthorityAndSubject(String authorityCode, String subjectCode, int offset, int limit) throws Exception
    {
        List<Grade> grades = new ArrayList<Grade>();
        
        String queryString = "&authority=" + authorityCode + "&subject=" + subjectCode;
        
        String apiResponse = getApiResponse("grade", queryString, offset, limit);
        
        JSONDeserializer<AbResponse> js = new JSONDeserializer<AbResponse>();
        AbResponse restApiResponse = js.deserialize(apiResponse, AbResponse.class);
        
        Status apiStatus = restApiResponse.getStatus();
        
        if(apiStatus != null)
        {
            if(apiStatus.getCode() == 200)
            {
                for(AbResource tmpResource : restApiResponse.getResources())
                {
                    AbData tmpData = tmpResource.getData();
                    
                    if(tmpData != null)
                    {
                        Grade tmpGrade = tmpData.getGrade();
                        
                        if(tmpGrade != null)
                        {
                            grades.add(tmpGrade);
                        }
                    }
                }
            }
            else
            {
                throw new Exception("Academic Benchmarks Error (" + apiStatus.getCode() + ") " + apiStatus.getCategory() + " - " + apiStatus.getEmsg());
            }
        }
        
        return grades;
    }
    
    /**
     * 
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    public List<Course> getCourses(int offset, int limit) throws Exception
    {
        List<Course> courses = new ArrayList<Course>();
        
        String apiResponse = getApiResponse("course", "", offset, limit);
        
        JSONDeserializer<AbResponse> js = new JSONDeserializer<AbResponse>();
        AbResponse restApiResponse = js.deserialize(apiResponse, AbResponse.class);
        
        Status apiStatus = restApiResponse.getStatus();
        
        if(apiStatus != null)
        {
            if(apiStatus.getCode() == 200)
            {
                for(AbResource tmpResource : restApiResponse.getResources())
                {
                    AbData tmpData = tmpResource.getData();
                    
                    if(tmpData != null)
                    {
                        Course tmpCourse = tmpData.getCourse();
                        
                        if(tmpCourse != null)
                        {
                            courses.add(tmpCourse);
                        }
                    }
                }
            }
            else
            {
                throw new Exception("Academic Benchmarks Error (" + apiStatus.getCode() + ") " + apiStatus.getCategory() + " - " + apiStatus.getEmsg());
            }
        }
        
        return courses;
    }
    
    /**
     * 
     * @param authorityCode
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    public List<Course> getCoursesByAuthority(String authorityCode, int offset, int limit) throws Exception
    {
        List<Course> courses = new ArrayList<Course>();
        
        String queryString = "&authority=" + authorityCode;
        
        String apiResponse = getApiResponse("course", queryString, offset, limit);
        
        JSONDeserializer<AbResponse> js = new JSONDeserializer<AbResponse>();
        AbResponse restApiResponse = js.deserialize(apiResponse, AbResponse.class);
        
        Status apiStatus = restApiResponse.getStatus();
        
        if(apiStatus != null)
        {
            if(apiStatus.getCode() == 200)
            {
                for(AbResource tmpResource : restApiResponse.getResources())
                {
                    AbData tmpData = tmpResource.getData();
                    
                    if(tmpData != null)
                    {
                        Course tmpCourse = tmpData.getCourse();
                        
                        if(tmpCourse != null)
                        {
                            courses.add(tmpCourse);
                        }
                    }
                }
            }
            else
            {
                throw new Exception("Academic Benchmarks Error (" + apiStatus.getCode() + ") " + apiStatus.getCategory() + " - " + apiStatus.getEmsg());
            }
        }
        
        return courses;
    }
    
    /**
     * 
     * @param authorityCode
     * @param subjectCode
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    public List<Course> getCoursesByAuthorityAndSubject(String authorityCode, String subjectCode, int offset, int limit) throws Exception
    {
        List<Course> courses = new ArrayList<Course>();
        
        String queryString = "&authority=" + authorityCode + "&subject=" + subjectCode;
        
        String apiResponse = getApiResponse("course", queryString, offset, limit);
        
        JSONDeserializer<AbResponse> js = new JSONDeserializer<AbResponse>();
        AbResponse restApiResponse = js.deserialize(apiResponse, AbResponse.class);
        
        Status apiStatus = restApiResponse.getStatus();
        
        if(apiStatus != null)
        {
            if(apiStatus.getCode() == 200)
            {
                for(AbResource tmpResource : restApiResponse.getResources())
                {
                    AbData tmpData = tmpResource.getData();
                    
                    if(tmpData != null)
                    {
                        Course tmpCourse = tmpData.getCourse();
                        
                        if(tmpCourse != null)
                        {
                            courses.add(tmpCourse);
                        }
                    }
                }
            }
            else
            {
                throw new Exception("Academic Benchmarks Error (" + apiStatus.getCode() + ") " + apiStatus.getCategory() + " - " + apiStatus.getEmsg());
            }
        }
        
        return courses;
    }
    
    /**
     * 
     * @param authorityCode
     * @param documentGuid
     * @param subjectDocGuid
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    public List<Course> getCoursesByAuthorityDocumentSubjectDoc(String authorityCode, String documentGuid, String subjectDocGuid, int offset, int limit) throws Exception
    {
        List<Course> courses = new ArrayList<Course>();
        
        String queryString = "&authority=" + authorityCode + "&document=" + documentGuid + "&subject_doc=" + subjectDocGuid;
        
        String apiResponse = getApiResponse("course", queryString, offset, limit);
        
        JSONDeserializer<AbResponse> js = new JSONDeserializer<AbResponse>();
        AbResponse restApiResponse = js.deserialize(apiResponse, AbResponse.class);
        
        Status apiStatus = restApiResponse.getStatus();
        
        if(apiStatus != null)
        {
            if(apiStatus.getCode() == 200)
            {
                for(AbResource tmpResource : restApiResponse.getResources())
                {
                    AbData tmpData = tmpResource.getData();
                    
                    if(tmpData != null)
                    {
                        Course tmpCourse = tmpData.getCourse();
                        
                        if(tmpCourse != null)
                        {
                            courses.add(tmpCourse);
                        }
                    }
                }
            }
            else
            {
                throw new Exception("Academic Benchmarks Error (" + apiStatus.getCode() + ") " + apiStatus.getCategory() + " - " + apiStatus.getEmsg());
            }
        }
        
        return courses;
    }
    
    /**
     * 
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    public List<Document> getDocuments(int offset, int limit) throws Exception
    {
        List<Document> documents = new ArrayList<Document>();
        
        String apiResponse = getApiResponse("document", "", offset, limit);
        
        JSONDeserializer<AbResponse> js = new JSONDeserializer<AbResponse>();
        AbResponse restApiResponse = js.deserialize(apiResponse, AbResponse.class);
        
        Status apiStatus = restApiResponse.getStatus();
        
        if(apiStatus != null)
        {
            if(apiStatus.getCode() == 200)
            {
                for(AbResource tmpResource : restApiResponse.getResources())
                {
                    AbData tmpData = tmpResource.getData();
                    
                    if(tmpData != null)
                    {
                        Document tmpDocument = tmpData.getDocument();
                        
                        if(tmpDocument != null)
                        {
                            documents.add(tmpDocument);
                        }
                    }
                }
            }
            else
            {
                throw new Exception("Academic Benchmarks Error (" + apiStatus.getCode() + ") " + apiStatus.getCategory() + " - " + apiStatus.getEmsg());
            }
        }
        
        return documents;
    }
    
    /**
     * 
     * @param authorityCode
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    public List<Document> getDocumentsByAuthority(String authorityCode, int offset, int limit) throws Exception
    {
        List<Document> documents = new ArrayList<Document>();
        
        String queryString = "&authority=" + authorityCode;
        
        String apiResponse = getApiResponse("document", queryString, offset, limit);
        
        JSONDeserializer<AbResponse> js = new JSONDeserializer<AbResponse>();
        AbResponse restApiResponse = js.deserialize(apiResponse, AbResponse.class);
        
        Status apiStatus = restApiResponse.getStatus();
        
        if(apiStatus != null)
        {
            if(apiStatus.getCode() == 200)
            {
                for(AbResource tmpResource : restApiResponse.getResources())
                {
                    AbData tmpData = tmpResource.getData();
                    
                    if(tmpData != null)
                    {
                        Document tmpDocument = tmpData.getDocument();
                        
                        if(tmpDocument != null)
                        {
                            documents.add(tmpDocument);
                        }
                    }
                }
            }
            else
            {
                throw new Exception("Academic Benchmarks Error (" + apiStatus.getCode() + ") " + apiStatus.getCategory() + " - " + apiStatus.getEmsg());
            }
        }
        
        return documents;
    }
    
    public List<Document> getDocumentsByAuthorityAndSubject(String authorityCode, String subjectCode, int offset, int limit) throws Exception
    {
        List<Document> documents = new ArrayList<Document>();
        
        String queryString = "&authority=" + authorityCode + "&subject=" + subjectCode;
        
        String apiResponse = getApiResponse("document", queryString, offset, limit);
        
        JSONDeserializer<AbResponse> js = new JSONDeserializer<AbResponse>();
        AbResponse restApiResponse = js.deserialize(apiResponse, AbResponse.class);
        
        Status apiStatus = restApiResponse.getStatus();
        
        if(apiStatus != null)
        {
            if(apiStatus.getCode() == 200)
            {
                for(AbResource tmpResource : restApiResponse.getResources())
                {
                    AbData tmpData = tmpResource.getData();
                    
                    if(tmpData != null)
                    {
                        Document tmpDocument = tmpData.getDocument();
                        
                        if(tmpDocument != null)
                        {
                            documents.add(tmpDocument);
                        }
                    }
                }
            }
            else
            {
                throw new Exception("Academic Benchmarks Error (" + apiStatus.getCode() + ") " + apiStatus.getCategory() + " - " + apiStatus.getEmsg());
            }
        }
        
        return documents;
    }
    
    /**
     * 
     * @param authorityCode
     * @param documentGuid
     * @param subjectDocGuid
     * @param courseGuid
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    public List<AbData> getTopLevelStandards(String authorityCode, String documentGuid, String subjectDocGuid, String courseGuid, int offset, int limit) throws Exception
    {
        List<AbData> standards = new ArrayList<AbData>();
        
        String queryString = "&authority=" + authorityCode + "&document=" + documentGuid + "&subject_doc=" + subjectDocGuid + "&course=" + courseGuid;
        
        String apiResponse = getApiResponse("", queryString, offset, limit);
        
        JSONDeserializer<AbResponse> js = new JSONDeserializer<AbResponse>();
        AbResponse restApiResponse = js.deserialize(apiResponse, AbResponse.class);
        
        Status apiStatus = restApiResponse.getStatus();
        
        if(apiStatus != null)
        {
            if(apiStatus.getCode() == 200)
            {
                for(AbResource tmpResource : restApiResponse.getResources())
                {
                    AbData tmpData = tmpResource.getData();
                    
                    if(tmpData != null)
                    {
                        if(tmpData.getLevel() == 1)
                        {
                            standards.add(tmpData);
                        }
                    }
                }
            }
            else
            {
                throw new Exception("Academic Benchmarks Error (" + apiStatus.getCode() + ") " + apiStatus.getCategory() + " - " + apiStatus.getEmsg());
            }
        }
        
        return standards;
    }
    
    /**
     * 
     * @param parentGuid
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    public List<AbData> getStandardChildredData(String parentGuid, int offset, int limit) throws Exception
    {
        List<AbData> standards = new ArrayList<AbData>();
        
        String queryString = "&parent=" + parentGuid;
        
        String apiResponse = getApiResponse("", queryString, offset, limit);
        
        JSONDeserializer<AbResponse> js = new JSONDeserializer<AbResponse>();
        AbResponse restApiResponse = js.deserialize(apiResponse, AbResponse.class);
        
        Status apiStatus = restApiResponse.getStatus();
        
        if(apiStatus != null)
        {
            if(apiStatus.getCode() == 200)
            {
                for(AbResource tmpResource : restApiResponse.getResources())
                {
                    AbData tmpData = tmpResource.getData();
                    
                    if(tmpData != null)
                    {
                        standards.add(tmpData);
                    }
                }
            }
            else
            {
                throw new Exception("Academic Benchmarks Error (" + apiStatus.getCode() + ") " + apiStatus.getCategory() + " - " + apiStatus.getEmsg());
            }
        }
        
        return standards;
    }
    
    /**
     * 
     * @param parentGuid
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    public List<Standard> getStandardChildred(String parentGuid, int offset, int limit) throws Exception
    {
        List<Standard> standards = new ArrayList<Standard>();
        
        String queryString = "&parent=" + parentGuid;
        
        String apiResponse = getApiResponse("", queryString, offset, limit);
        
        JSONDeserializer<AbResponse> js = new JSONDeserializer<AbResponse>();
        AbResponse restApiResponse = js.deserialize(apiResponse, AbResponse.class);
        
        Status apiStatus = restApiResponse.getStatus();
        
        if(apiStatus != null)
        {
            if(apiStatus.getCode() == 200)
            {
                for(AbResource tmpResource : restApiResponse.getResources())
                {
                    AbData tmpData = tmpResource.getData();
                    
                    if(tmpData != null)
                    {
                        Standard standardModel = getStandard(tmpData.getGuid());
                        standards.add(standardModel);
                    }
                }
            }
            else
            {
                throw new Exception("Academic Benchmarks Error (" + apiStatus.getCode() + ") " + apiStatus.getCategory() + " - " + apiStatus.getEmsg());
            }
        }
        
        return standards;
    }
    
    /**
     * 
     * @param guid
     * @return
     * @throws Exception
     */
    public Standard getStandard(String guid) throws Exception
    {
        Standard standardModel = null;
        
        String baseUrl = AB_API_URL_V3 + "/" + guid;
        
        String apiResponse = getApiResponse(baseUrl, "", "", 0, 1);
        
        JSONDeserializer<AbResponse> js = new JSONDeserializer<AbResponse>();
        AbResponse restApiResponse = js.deserialize(apiResponse, AbResponse.class);
        
        Status apiStatus = restApiResponse.getStatus();
        
        if(apiStatus != null)
        {
            if(apiStatus.getCode() == 200)
            {
                for(AbResource tmpResource : restApiResponse.getResources())
                {
                    AbData tmpData = tmpResource.getData();
                    
                    if(tmpData != null)
                    {
                        standardModel = new Standard();
                        standardModel.setStatus(tmpData.getStatus());
                        standardModel.setPlaceholder(tmpData.getPlaceholder());
                        standardModel.setGuid(tmpData.getGuid());
                        standardModel.setDescr(tmpData.getDescr());
                        standardModel.setAdopt_year(tmpData.getAdopt_year());
                        standardModel.setSelf(tmpData.getSelf());
                        standardModel.setDate_modified(tmpData.getDate_modified());
                        standardModel.setDeepest(tmpData.getDeepest());
                        standardModel.setNumber(tmpData.getNumber());
                        standardModel.setVersion(tmpData.getVersion());
                        standardModel.setLabel(tmpData.getLabel());
                        standardModel.setStem(tmpData.getStem());
                        standardModel.setSeq(tmpData.getSeq());
                        standardModel.setExtended_descr(tmpData.getExtended_descr());

                        if(tmpData.getAuthority() != null)
                        {
                            standardModel.setAuthorityGuid(tmpData.getAuthority().getGuid());
                            standardModel.setAuthorityCode(tmpData.getAuthority().getCode());
                            standardModel.setAuthorityDescr(tmpData.getAuthority().getDescr());
                        }
                        
                        if(tmpData.getDocument() != null)
                        {
                            standardModel.setDocumentGuid(tmpData.getDocument().getGuid());
                            standardModel.setDocumentTitle(tmpData.getDocument().getTitle());
                        }
                        
                        if(tmpData.getSubject() != null)
                        {
                            standardModel.setSubjectCode(tmpData.getSubject().getCode());
                            standardModel.setSubjectDescr(tmpData.getSubject().getDescr());
                        }
                        
                        if(tmpData.getSubject_doc() != null)
                        {
                            standardModel.setSubjectDocGuid(tmpData.getSubject_doc().getGuid());
                            standardModel.setSubjectDocDescr(tmpData.getSubject_doc().getDescr());
                        }
                        
                        if(tmpData.getCourse() != null)
                        {
                            standardModel.setCourseGuid(tmpData.getCourse().getGuid());
                            standardModel.setCourseDescr(tmpData.getCourse().getDescr());
                        }
                    }
                }
            }
            else
            {
                throw new Exception("Academic Benchmarks Error (" + apiStatus.getCode() + ") " + apiStatus.getCategory() + " - " + apiStatus.getEmsg());
            }
        }
        
        return standardModel;
    }
    
    /**
     * 
     * @param list
     * @param queryString
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    private String getApiResponse(String list, String queryString, int offset, int limit) throws Exception
    {
        return getApiResponse(AB_API_URL_V3, list, queryString, offset, limit);
    }
    
    /**
     * 
     * @param baseUrl
     * @param list
     * @param queryString
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    private String getApiResponse(String baseUrl, String list, String queryString, int offset, int limit) throws Exception
    {
        WebPost webPostUtil = new WebPost();
        
        logger.debug("Connecting to Academic Benchmarks API");
        
        StringBuffer parameters = new StringBuffer();
        parameters.append("&offset=" + offset);
        parameters.append("&limit=" + limit);
        
        if(!StringUtil.isNullOrEmpty(list))
        {
            parameters.append("&list=" + list);
        }
        
        if(!StringUtil.isNullOrEmpty(queryString))
        {
            parameters.append(queryString);
        }
        
        // Build the full API url for the transaction
        String fullApiUrl = baseUrl + "?" + createNewSignature() + parameters.toString();
        
        webPostUtil.secureConnect(fullApiUrl, "text/plain; charset=utf-8", "GET");
        
        // Get the response for the API
        String apiResponse = webPostUtil.secureReceive();
        logger.debug("Academic Benchmarks API Response: " + apiResponse);
        
        logger.debug("Disconnecting from Academic Benchmarks API");
        webPostUtil.secureDisconnect();
        
        return apiResponse;
    }
    
    /**
     * 
     * @return
     */
    private String createNewSignature()
    {
        int lifespan = m_signatureLifespanMinutes;
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, lifespan);
        
        Date expirationDate = cal.getTime();
        
        long expirationInMillis = expirationDate.getTime();
        String expirationStr = Long.toString(expirationInMillis);

        String signature = getSignatureHash(expirationStr);
        String queryString = "partner.id=" + m_partnerId + "&auth.signature=" + signature + "&auth.expires=" + expirationStr + "&user.id=" + m_userId;

        return queryString;
    }
    
    /**
     * 
     * @param expiration
     * @return
     */
    private String getSignatureHash(String expiration)
    {
        String hash = null;

        try
        {
            String message = expiration + "\n" + m_userId;
            Mac sha256HMAC = Mac.getInstance(HASH_ALGORITHM);
            
            SecretKeySpec secretKey = new SecretKeySpec(m_partnerKey.getBytes(), HASH_ALGORITHM);
            
            sha256HMAC.init(secretKey);
            
            hash = Base64.encodeBase64String(sha256HMAC.doFinal(message.getBytes()));
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }

        return hash;
    }
}