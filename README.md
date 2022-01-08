# Spring Content Solr Example
Example repository for lost souls such I was who want to do full text search with spring content and solr.

## Solr config

You will find required configuration files to create your solr core (document store) for example in the admin page.

In solrconfig.xml added:
   1. The Solr Cell request handler  (https://solr.apache.org/guide/6_6/uploading-data-with-solr-cell-using-apache-tika.html) This will enable to use several file format's content stored for full text search (example PDF)

 ```
<requestHandler name="/update/extract"
                startup="lazy"
                class="solr.extraction.ExtractingRequestHandler" >
	  <lst name="defaults">
		<str name="lowernames">true</str>
		<str name="fmap.content">_text_</str>
	  </lst>
	</requestHandler>
```
In schema.xml added:
1. Fields which will store the entities data
 ```
<field name="id" type="string" indexed="true" stored="true" required="true" multiValued="false" />
<field name="title" type="string" indexed="true" stored="true" required="true" multiValued="false" />
```

2. Copy field to \__text__ which will store the whole content of the file for full text search
```
<copyField source="*" dest="_text_"/>
```