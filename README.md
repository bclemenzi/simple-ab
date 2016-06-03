# Academic Benchmarks Java Library

## What is Academic Benchmarks?

[Academic Benchmarks](http://academicbenchmarks.com/) provides an API that allows a user with a subscription to 
download various [CBE standards](http://www.competencyworks.org/analysis/what-is-the-difference-between-standards-based-grading/).

Features
--------

  * Supports Academic Benchmarks API v3
  * Supports Academic Benchmarks RESTful "standards" resource
  * Fully implemented authentication system.  Just enter your Academic Benchmarks credentials and go!
  * Fully constructed and inflated document trees
  * Published on Maven Central Repository

Getting started
---------------
Including the Java library in your project

The easiest way to incorporate the library into your Java project is to use Maven. Simply add a new dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.nfbsoftware</groupId>
	<artifactId>simple-ab</artifactId>
	<version>1.0.4</version>
</dependency>
```

Get some credentials
-----

First you need some credentials (a "Partner ID" and a "Partner Key").  Either get some by paying Academic Benchmarks 
some money, or by [signing up for a sandbox account](http://docs.academicbenchmarks.com/#?d=support&f=request_demo).

Usage
-----
Client Options

```java	
String abPartnerId = "25...89e9";  // Set your unique partner key. Obtained from AB Support.
String abPartnerKey = "25...89e9";  // Set your unique partner identifier. Obtained from AB Support.
String userId = "25...89e9";  // Set a GUID to be used in creating signature key when making calls to the Academic Benchmarks API 

// Init our client object
AcademicBenchmarksClient abClient = new AcademicBenchmarksClient(abPartnerId, abPartnerKey, userId);
```

```java	
String abPartnerId = "25...r43t";  // Set your unique partner key. Obtained from AB Support.
String abPartnerKey = "45...89e9";  // Set your unique partner identifier. Obtained from AB Support.
String userId = "67...1234";  // Set a GUID to be used in creating signature key when making calls to the Academic Benchmarks API 
int signatureLifespanMinutes = 120; // Set the number of minutes our signature will live for.

// Init our client object
AcademicBenchmarksClient abClient = new AcademicBenchmarksClient(abPartnerId, abPartnerKey, userId, signatureLifespanMinutes);
```

List the Authorities available to your Academic Benchmarks API license

```java	
// Get a paged list (first 10) of the Authorities available to you.
List<Authority> authorities = abClient.getAuthorities(0, 10);

// Loop through the returned Authorities
for(Authority tmpModel : authorities)
{
	System.out.println("Authority GUID: " + tmpModel.getGuid());
	System.out.println("Authority Code: " + tmpModel.getCode());
	System.out.println("Authority Description: " + tmpModel.getDescr());
}
```

List the Subjects available to your Academic Benchmarks API license

```java	
// Get a paged list (first 10) of the Subjects available to you.
List<Subject> subjects = abClient.getSubjects(0, 10);

// Loop through the returned Subjects
for(Subject tmpModel : subjects)
{
    System.out.println("Subject Code: " + tmpModel.getCode());
    System.out.println("Subject Description: " + tmpModel.getDescr());
}
```

List the Documents available for a given Authority

```java	
// Get an authorityCode, usually from running the getAuthorities method above.
String authorityCode = "OH";

// Get a paged list (first 10) of the Documents available to the given Authority.
List<Document> documents = abClient.getDocumentsByAuthority(authorityCode, 0, 10);

// Loop through the returned Document
for(Document tmpModel : documents)
{
    System.out.println("Document GUID: " + tmpModel.getGuid());
    System.out.println("Document Title: " + tmpModel.getTitle());
}
```

List the Subject Documents available for a given Authority and Document

```java	
// Get an authorityCode and documentGuid, usually from running the getAuthorities and getDocumentsByAuthority methods above.
String authorityCode = "OH";
String documentGuid = "E1D5D8B6-DA22-11E2-95B3-3B359DFF4B22";

// Get a paged list (first 10) of the Subject Documents available.
List<SubjectDocument> subjectDocuments = abClient.getSubjectDocumentsByAuthorityAndDocument(authorityCode, documentGuid, 0, 10);

// Loop through the returned Subject Documents
for(SubjectDocument tmpModel : subjectDocuments)
{
    System.out.println("SubjectDocument GUID: " + tmpModel.getGuid());
    System.out.println("SubjectDocument Description: " + tmpModel.getDescr());
}
```

List the Courses available for a given Authority, Document, and Subject Document

```java	
// Get an authorityCode and documentGuid, usually from running the getAuthorities and getDocumentsByAuthority methods above.
String authorityCode = "OH";
String documentGuid = "E1D5D8B6-DA22-11E2-95B3-3B359DFF4B22";
String subjectDocGuid = "E1C6ECD4-DA22-11E2-95B3-3B359DFF4B22";

// Get a paged list (first 10) of the Courses available.
List<Course> courses = abClient.getCoursesByAuthorityDocumentSubjectDoc(authorityCode, documentGuid, subjectDocGuid, 0, 10);

// Loop through the returned Courses
for(Course tmpModel : courses)
{
    System.out.println("Course GUID: " + tmpModel.getGuid());
    System.out.println("Course Description: " + tmpModel.getDescr());
}
```

List the top level standards available for a given Authority, Document, Subject Document, and Course

```java	
// Get an authorityCode and documentGuid, usually from running the getAuthorities and getDocumentsByAuthority methods above.
String authorityCode = "OH";
String documentGuid = "E1D5D8B6-DA22-11E2-95B3-3B359DFF4B22";
String subjectDocGuid = "E1C6ECD4-DA22-11E2-95B3-3B359DFF4B22";
String courseGuid = "E1743C5A-DA22-11E2-95B3-3B359DFF4B22";

// Get a paged list (first 10) of the Standards available.
List<AbData> standards = abClient.getTopLevelStandards(authorityCode, documentGuid, subjectDocGuid, courseGuid, 0, 10);

// Loop through the returned Courses
for(AbData tmpModel : standards)
{
    System.out.println("AbData GUID (standard GUID): " + tmpModel.getGuid());
    System.out.println("AbData Number: " + tmpModel.getNumber());
    System.out.println("AbData Description: " + tmpModel.getDescr());
    System.out.println("AbData Adopt Year: " + tmpModel.getAdopt_year());
    System.out.println("AbData Level: " + tmpModel.getLevel());
    System.out.println("AbData Self: " + tmpModel.getSelf());
}
```

Get standard by GUID

```java	
// Get a standardGuid, usually from running the getTopLevelStandards method above.
String standardGuid = "AEE4CB82-D9DD-11E2-87A3-00249DFF4B22";

Standard standardModel = abClient.getStandard(standardGuid);

if(standardModel != null)
{
    System.out.println("Standard Adopt Year: " + standardModel.getAdopt_year());
    System.out.println("Standard AuthorityCode: " + standardModel.getAuthorityCode());
    System.out.println("Standard AuthorityDescr: " + standardModel.getAuthorityDescr());
    System.out.println("Standard AuthorityGuid: " + standardModel.getAuthorityGuid());
    System.out.println("Standard CourseDescr: " + standardModel.getCourseDescr());
    System.out.println("Standard CourseGuid: " + standardModel.getCourseGuid());
    System.out.println("Standard Date Modified: " + standardModel.getDate_modified());
    System.out.println("Standard Deepest: " + standardModel.getDeepest());
    System.out.println("Standard Description: " + standardModel.getDescr());
    System.out.println("Standard DocumentGuid: " + standardModel.getDocumentGuid());
    System.out.println("Standard DocumentTitle: " + standardModel.getDocumentTitle());
    System.out.println("Standard Extended Description: " + standardModel.getExtended_descr());
    System.out.println("Standard GUID: " + standardModel.getGuid());
    System.out.println("Standard Label: " + standardModel.getLabel());
    System.out.println("Standard Level: " + standardModel.getLevel());
    System.out.println("Standard Number: " + standardModel.getNumber());
    System.out.println("Standard Placeholder: " + standardModel.getPlaceholder());
    System.out.println("Standard Self: " + standardModel.getSelf());
    System.out.println("Standard Seq: " + standardModel.getSeq());
    System.out.println("Standard Status: " + standardModel.getStatus());
    System.out.println("Standard Stem: " + standardModel.getStem());
    System.out.println("Standard SubjectCode: " + standardModel.getSubjectCode());
    System.out.println("Standard SubjectDescr: " + standardModel.getSubjectDescr());
    System.out.println("Standard SubjectDocDescr: " + standardModel.getSubjectDocDescr());
    System.out.println("Standard SubjectDocGuid: " + standardModel.getSubjectDocGuid());
    System.out.println("Standard Version: " + standardModel.getVersion());
}
```

Get standard by Number

```java	
String authorityCode = "OH";
String standardNumber = "OH.Math.Content.1.OA";

Standard standardModel = abClient.getStandardByNumber(authorityCode, standardNumber);

if(standardModel != null)
{
    System.out.println("Standard Adopt Year: " + standardModel.getAdopt_year());
    System.out.println("Standard AuthorityCode: " + standardModel.getAuthorityCode());
    System.out.println("Standard AuthorityDescr: " + standardModel.getAuthorityDescr());
    System.out.println("Standard AuthorityGuid: " + standardModel.getAuthorityGuid());
    System.out.println("Standard CourseDescr: " + standardModel.getCourseDescr());
    System.out.println("Standard CourseGuid: " + standardModel.getCourseGuid());
    System.out.println("Standard Date Modified: " + standardModel.getDate_modified());
    System.out.println("Standard Deepest: " + standardModel.getDeepest());
    System.out.println("Standard Description: " + standardModel.getDescr());
    System.out.println("Standard DocumentGuid: " + standardModel.getDocumentGuid());
    System.out.println("Standard DocumentTitle: " + standardModel.getDocumentTitle());
    System.out.println("Standard Extended Description: " + standardModel.getExtended_descr());
    System.out.println("Standard GUID: " + standardModel.getGuid());
    System.out.println("Standard Label: " + standardModel.getLabel());
    System.out.println("Standard Level: " + standardModel.getLevel());
    System.out.println("Standard Number: " + standardModel.getNumber());
    System.out.println("Standard Placeholder: " + standardModel.getPlaceholder());
    System.out.println("Standard Self: " + standardModel.getSelf());
    System.out.println("Standard Seq: " + standardModel.getSeq());
    System.out.println("Standard Status: " + standardModel.getStatus());
    System.out.println("Standard Stem: " + standardModel.getStem());
    System.out.println("Standard SubjectCode: " + standardModel.getSubjectCode());
    System.out.println("Standard SubjectDescr: " + standardModel.getSubjectDescr());
    System.out.println("Standard SubjectDocDescr: " + standardModel.getSubjectDocDescr());
    System.out.println("Standard SubjectDocGuid: " + standardModel.getSubjectDocGuid());
    System.out.println("Standard Version: " + standardModel.getVersion());
}
```

Get the standards children for a parent standard

```java	
// Get a standardGuid, usually from running the getTopLevelStandards method above.
String standardGuid = "AEB3064C-D9DD-11E2-87A3-00249DFF4B22";

List<Standard> childStandards = abClient.getStandardChildred(standardGuid, 0, 100);

int counter = 0;
for(Standard childStandardModel : childStandards)
{
    System.out.println(counter + " Standard Adopt Year: " + childStandardModel.getAdopt_year());
    System.out.println(counter + " Standard AuthorityCode: " + childStandardModel.getAuthorityCode());
    System.out.println(counter + " Standard AuthorityDescr: " + childStandardModel.getAuthorityDescr());
    System.out.println(counter + " Standard AuthorityGuid: " + childStandardModel.getAuthorityGuid());
    System.out.println(counter + " Standard CourseDescr: " + childStandardModel.getCourseDescr());
    System.out.println(counter + " Standard CourseGuid: " + childStandardModel.getCourseGuid());
    System.out.println(counter + " Standard Date Modified: " + childStandardModel.getDate_modified());
    System.out.println(counter + " Standard Deepest: " + childStandardModel.getDeepest());
    System.out.println(counter + " Standard Description: " + childStandardModel.getDescr());
    System.out.println(counter + " Standard DocumentGuid: " + childStandardModel.getDocumentGuid());
    System.out.println(counter + " Standard DocumentTitle: " + childStandardModel.getDocumentTitle());
    System.out.println(counter + " Standard Extended Description: " + childStandardModel.getExtended_descr());
    System.out.println(counter + " Standard Guid: " + childStandardModel.getGuid());
    System.out.println(counter + " Standard Label: " + childStandardModel.getLabel());
    System.out.println(counter + " Standard Level: " + childStandardModel.getLevel());
    System.out.println(counter + " Standard Number: " + childStandardModel.getNumber());
    System.out.println(counter + " Standard Placeholder: " + childStandardModel.getPlaceholder());
    System.out.println(counter + " Standard Self: " + childStandardModel.getSelf());
    System.out.println(counter + " Standard Seq: " + childStandardModel.getSeq());
    System.out.println(counter + " Standard Status: " + childStandardModel.getStatus());
    System.out.println(counter + " Standard Stem: " + childStandardModel.getStem());
    System.out.println(counter + " Standard SubjectCode: " + childStandardModel.getSubjectCode());
    System.out.println(counter + " Standard SubjectDescr: " + childStandardModel.getSubjectDescr());
    System.out.println(counter + " Standard SubjectDocDescr: " + childStandardModel.getSubjectDocDescr());
    System.out.println(counter + " Standard SubjectDocGuid: " + childStandardModel.getSubjectDocGuid());
    System.out.println(counter + " Standard Version: " + childStandardModel.getVersion());
    
    counter++;
}
```

Get the standards children data for a parent standard.  Loading the objects with the getStandardChildredData method is a faster transaction than the getStandardChildred> call sampled above.

```java	
// Get a standardGuid, usually from running the getTopLevelStandards method above.
String standardGuid = "AEB3064C-D9DD-11E2-87A3-00249DFF4B22";

List<AbData> childStandardsData = abClient.getStandardChildredData(standardGuid, 0, 100);

int childCounter = 0;
for(AbData childStandardModel : childStandardsData)
{
    System.out.println(childCounter + " Standard Data Adopt Year: " + childStandardModel.getAdopt_year());
    System.out.println(childCounter + " Standard Data Date Modified: " + childStandardModel.getDate_modified());
    System.out.println(childCounter + " Standard Data Deepest: " + childStandardModel.getDeepest());
    System.out.println(childCounter + " Standard Data Description: " + childStandardModel.getDescr());
    System.out.println(childCounter + " Standard Data Extended Description: " + childStandardModel.getExtended_descr());
    System.out.println(childCounter + " Standard Data Guid: " + childStandardModel.getGuid());
    System.out.println(childCounter + " Standard Data Label: " + childStandardModel.getLabel());
    System.out.println(childCounter + " Standard Data Level: " + childStandardModel.getLevel());
    System.out.println(childCounter + " Standard Data Number: " + childStandardModel.getNumber());
    System.out.println(childCounter + " Standard Data Placeholder: " + childStandardModel.getPlaceholder());
    System.out.println(childCounter + " Standard Data Self: " + childStandardModel.getSelf());
    System.out.println(childCounter + " Standard Data Seq: " + childStandardModel.getSeq());
    System.out.println(childCounter + " Standard Data Status: " + childStandardModel.getStatus());
    System.out.println(childCounter + " Standard Data Stem: " + childStandardModel.getStem());
    System.out.println(childCounter + " Standard Data Version: " + childStandardModel.getVersion());
    
    // You may look up the hierarchy at the data level as well; you'll just need to call into the various opbjects for that data
    if(childStandardModel.getAuthority() != null)
    {
        System.out.println(childCounter + " Standard Data AuthorityCode: " + childStandardModel.getAuthority().getCode());
        System.out.println(childCounter + " Standard Data AuthorityDescr: " + childStandardModel.getAuthority().getDescr());
        System.out.println(childCounter + " Standard Data AuthorityGuid: " + childStandardModel.getAuthority().getGuid());
    }
    
    childCounter++;
}
```

Get the FULL standard document tree from a given Standard object

```java	
public void testFullTreeDump() throws Exception
{
	String standardGuid = "AEB3064C-D9DD-11E2-87A3-00249DFF4B22";

	// Get our top level standard by the GUID passed in
	Standard standardModel = abClient.getStandard(standardGuid);
            
    if(standardModel != null)
    {
		// Request the FULL standard tree of objects
		Standard fullStandardModel = abClient.getStandardDocument(standardModel);
	
		// Loop though all of the descendants by calling a private method recursively 
		processChildren(0, fullStandardModel);
	}
}

private void processChildren(int level, Standard standardModel)
{
    System.out.println((level++) + " Standard: " + standardModel.getNumber() + " Deepest: " + standardModel.getDeepest() + "  " + standardModel.getDescr());
    
    for(Standard childStandard : standardModel.getStandards())
    {
        processChildren(level, childStandard);
    }
}
```



