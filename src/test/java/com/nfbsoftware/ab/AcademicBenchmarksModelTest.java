package com.nfbsoftware.ab;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.nfbsoftware.ab.model.AbData;
import com.nfbsoftware.ab.model.AbResource;
import com.nfbsoftware.ab.model.Authority;
import com.nfbsoftware.ab.model.Status;

import flexjson.JSONDeserializer;

/**
 * 
 * @author Brendan Clemenzi
 * @email brendan@clemenzi.com
 */
public class AcademicBenchmarksModelTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AcademicBenchmarksModelTest( String testName )
    {
        super( testName );
    }
    
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AcademicBenchmarksModelTest.class );
    }
    
    /**
     * 
     * @throws Exception
     */
    public void testAuthority() throws Exception
    {
        System.out.println("====> Starting AcademicBenchmarksModelTest.testAuthority");
        
        try
        {
            String authorityJsonString = "{\"descr\":\"Alabama\",\"guid\":\"A8325A6C-901A-11DF-A622-0C319DFF4B22\",\"code\":\"AL\"}";
            
            JSONDeserializer<Authority> js = new JSONDeserializer<Authority>();
            Authority tmpModel = js.deserialize(authorityJsonString, Authority.class);
            
            System.out.println("Authority GUID: " + tmpModel.getGuid());
            System.out.println("Authority Code: " + tmpModel.getCode());
            System.out.println("Authority Description: " + tmpModel.getDescr());

            assertTrue(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            assertTrue(false);
        }
        
        System.out.println("====> Finished AcademicBenchmarksModelTest.testAuthority");
    }
    
    /**
     * 
     * @throws Exception
     */
    public void testAbData() throws Exception
    {
        System.out.println("====> Starting AcademicBenchmarksModelTest.testAbData");
        
        try
        {
            String abDataJsonString = "{\"authority\":{\"descr\":\"Alabama\",\"guid\":\"A8325A6C-901A-11DF-A622-0C319DFF4B22\",\"code\":\"AL\"}}";
            
            JSONDeserializer<AbData> js = new JSONDeserializer<AbData>();
            AbData tmpModel = js.deserialize(abDataJsonString, AbData.class);
            
            Authority tmpAuthority = tmpModel.getAuthority();
            
            System.out.println("AbData Authority GUID: " + tmpAuthority.getGuid());
            System.out.println("AbData Authority Code: " + tmpAuthority.getCode());
            System.out.println("AbData Authority Description: " + tmpAuthority.getDescr());

            assertTrue(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            assertTrue(false);
        }
        
        System.out.println("====> Finished AcademicBenchmarksModelTest.testAbData");
    }
    
    /**
     * 
     * @throws Exception
     */
    public void testAbResource() throws Exception
    {
        System.out.println("====> Starting AcademicBenchmarksModelTest.testAbResource");
        
        try
        {
            String abDataJsonString = "{\"data\":{\"authority\":{\"descr\":\"Alabama\",\"guid\":\"A8325A6C-901A-11DF-A622-0C319DFF4B22\",\"code\":\"AL\"}}}";
            
            JSONDeserializer<AbResource> js = new JSONDeserializer<AbResource>();
            AbResource tmpModel = js.deserialize(abDataJsonString, AbResource.class);
            
            AbData tmpAbData = tmpModel.getData();
            Authority tmpAuthority = tmpAbData.getAuthority();
            
            System.out.println("testAbResource Authority GUID: " + tmpAuthority.getGuid());
            System.out.println("testAbResource Authority Code: " + tmpAuthority.getCode());
            System.out.println("testAbResource Authority Description: " + tmpAuthority.getDescr());

            assertTrue(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            assertTrue(false);
        }
        
        System.out.println("====> Finished AcademicBenchmarksModelTest.testAbResource");
    }
    
    /**
     * 
     * @throws Exception
     */
    public void testStatus() throws Exception
    {
        System.out.println("====> Starting AcademicBenchmarksModelTest.testStatus");
        
        try
        {
            String abDataJsonString = "{\"imsg\":\"\",\"code\":200,\"category\":\"OK\",\"emsg\":\"\"}";
            
            JSONDeserializer<Status> js = new JSONDeserializer<Status>();
            Status tmpModel = js.deserialize(abDataJsonString, Status.class);
            
            System.out.println("testStatus Category: " + tmpModel.getCategory());
            System.out.println("testStatus Emsg: " + tmpModel.getEmsg());
            System.out.println("testStatus Imsg: " + tmpModel.getImsg());
            System.out.println("testStatus Code: " + tmpModel.getCode());

            assertTrue(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            assertTrue(false);
        }
        
        System.out.println("====> Finished AcademicBenchmarksModelTest.testStatus");
    }
}
