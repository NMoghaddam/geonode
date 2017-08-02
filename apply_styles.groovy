
String path = "/run/media/sachin/SACHIN4TB/data/"

new File(path).eachFile { f->
    if (f.getName().endsWith(".sld")){
	fn = f.getName().replace(".sld", "").trim()	
	String cmd = 'curl -u admin:geoserver -XPUT -H "Content-type: text/xml" -d "<layer><defaultStyle><name>' + fn + '</name></defaultStyle><enabled>true</enabled></layer>" http://192.168.33.100/geoserver/rest/layers/' + fn
	println(cmd)

    }
}
