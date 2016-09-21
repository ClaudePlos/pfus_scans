<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Upload</title>
</head>
  
<body>
 
    <hr/>
  
    <fieldset>
        <legend>Upload File</legend>
        <form action="fileuploadservlet" method="post" enctype="multipart/form-data">
            <label for="filename_1">File: </label>
            <input id="filename_1" type="file" name="filename_1" size="50"/><br/>
            <br/>
            <input type="submit" value="Upload File"/>
            <!-- <br><center><img src="loader.gif" style="width:256px;height:256px;"></center> -->
        </form>
    </fieldset>
    
</body>
</html>