import io._
import sys.process._
import java.net.URL
import java.io.File

System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.75 Safari/535.7")

//pcrafi
/*
val urlShort:String = "http://pcrafi.spc.int/layers/geonode:"
val urlTarget:String = "http://pcrafi.spc.int/catalogue/csw?outputschema=http://www.isotc211.org/2005/gmd&service=CSW&request=GetRecordById&version=2.0.2&elementsetname=full&id="
val path = "/home/sachin/tmp/metadata/pcrafi/"
val pathDoc = "/home/sachin/tmp/metadata/pcrafi-doc/"
 */
//pacgeo
val urlShort:String = "http://www.pacgeo.org/layers/geonode:"
val urlTarget:String = "http://www.pacgeo.org/catalogue/csw?outputschema=http://www.isotc211.org/2005/gmd&service=CSW&request=GetRecordById&version=2.0.2&elementsetname=full&id="
val path = "/home/sachin/tmp/metadata/pacgeo/"
val pathDoc = "/home/sachin/tmp/metadata/pacgeo-doc/"

/*
\copy (select uuid, title, distribution_url from base_resourcebase) TO '/tmp/uuid.csv' with CSV;
\copy (select * from documents_document) TO '/tmp/pacgeo-documents.csv' with CSV HEADER;

 */
for (line <- Source.fromFile("pacgeo-uuid.csv").getLines()) {
  val arr = line.split(",")
  if (arr.length > 2){
    val uuid = arr(0)
    val title = arr(1)
    val url = arr(2)    
    if (!url.contains("documents")){
      val filename = url.replace(urlShort, "").trim() + ".xml"
      println(uuid + " : " + filename)
      val target = urlTarget + uuid
      println(target)
      new URL(target) #> new File(path + filename) !!
    }
    if (url.contains("documents")){
      val filename = title.replaceAll(" ", "_") + ".xml"
      println(uuid + " : " + filename)
      val target = urlTarget + uuid
      println(target)
      new URL(target) #> new File(pathDoc + filename) !!
    }
  }
}

println("FINISHED.")

