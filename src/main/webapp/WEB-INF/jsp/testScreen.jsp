<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>
   
    <meta charset="utf-8">
    <title>C M T</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
   
	<link id="bs-css" href="css/bootstrap-cerulean.min.css" rel="stylesheet">
    <link href="css/charisma-app.css" rel="stylesheet">
    <link href='bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
    <link href='bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
    <link href='bower_components/chosen/chosen.min.css' rel='stylesheet'>
    <link href='bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
    <link href='bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
    <link href='bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
    <link href='css/jquery.noty.css' rel='stylesheet'>
    <link href='css/noty_theme_default.css' rel='stylesheet'>
    <link href='css/elfinder.min.css' rel='stylesheet'>
    <link href='css/elfinder.theme.css' rel='stylesheet'>
    <link href='css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='css/uploadify.css' rel='stylesheet'>
    <link href='css/animate.min.css' rel='stylesheet'>
	
    <!-- jQuery -->
    <script src="bower_components/jquery/jquery.min.js"></script>



    
    <!-- The fav icon -->
    <link rel="shortcut icon" href="img/favicon.ico">
    
    
    <style>
      #map_canvas {
        width: 100%;
        height: 420px;
        background-color: #CCC;
      }
      
      .bs-example{
    	margin: 20px;
    }
	/* Fix alignment issue of label on extra small devices in Bootstrap 3.2 */
    .form-horizontal .control-label{
        padding-top: 7px;
        margin-left: 15%;
    }
    
    .error {
	color: #ff0000;
}
 
.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
    </style>

<script type="text/javascript">
$(document).ready(function(){


 $('#selectError2').on('change', function() {
		var imeiNumber = $("#selectError2").val(); 
        dataString = "imeiNumber=" + imeiNumber;
		
		$.ajax({
            type: "POST",
            url: "test",
            data: dataString,
            dataType: "json",
            
            //if received a response from the server
            success: function( data, textStatus, jqXHR) {
                //our country code was correct so we have some information to display
                 if(data.success){
                	 $('#inputSimCardNo').val(data.testTrackerInfo.sim);
                	 $('#inputSimCardProvider').val(data.testTrackerInfo.provider);
                	 $('#inputPhoneNumber').val(data.testTrackerInfo.mobile);
                   
                 } 
                 //display error message
                 else {
                     $("#ajaxResponse").html("<div><b>Invalid Vehicle Number!</b></div>");
                 }
            },
            
            //If there was no resonse from the server
            error: function(jqXHR, textStatus, errorThrown){
                 console.log("Something really bad happened " + textStatus);
                  $("#ajaxResponse").html(jqXHR.responseText);
            },
            
            //capture the request before it was sent to server
            beforeSend: function(jqXHR, settings){
                //adding some Dummy data to the request
                settings.data += "&dummyData=whatever";
                //disable the button until we get the response
                $('#myButton').attr("disabled", true);
            },
            
            //this is called after the response or error functions are finsihed
            //so that we can take some action
            complete: function(jqXHR, textStatus){
                //enable the button 
                $('#myButton').attr("disabled", false);
            }
  
        });        
});

	});

</script>
</head>

<body >
   <%@include file="Header.jsp" %>
<div class="ch-container">
    <div class="row">
        <%@include file="LeftMenu.jsp" %>
       <noscript>
            <div class="alert alert-block col-md-12">
                <h4 class="alert-heading">Warning!</h4>

                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
                    enabled to use this site.</p>
            </div>
        </noscript>

        <div id="content" class="col-lg-9 col-sm-9">
            <!-- content starts -->
            <div>
    <ul class="breadcrumb">
        <li>
            <a href="home">Home</a>
        </li>
        <li>
            <a href="testScreen">Test Tracker</a>
        </li>
    </ul>
</div>
<div class=" row">
  
    
</div>


<div class="row">
   
    <!--/span-->

    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon glyphicon-user"></i> Test Tracker</h2>

                <div class="box-icon">
                    <a href="#" class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>
                </div>
            </div>
            <div class="box-content">
                <div class="box-content">
                    <div class="bs-example">
    
    
    <form:form method="post" action="./testTracker" modelAttribute="testTracker" class="form-horizontal" >
        <div class="form-group">
            <label for="inputIMEI" class="control-label col-xs-2">IMEI Number</label>
            <div class="col-xs-5">
                      <form:select path="imei" id="selectError2" data-rel="chosen" >
                      
                      <form:option value="NONE" label="----------- Select ----------"/>
                      <form:options items="${map.imeiList}" />
                      </form:select>
                      <form:errors path="imei" cssClass="error" id="ajaxResponse"/>
            </div>
        </div>
        <%-- <div class="form-group">
            <label for="inputtrackerType" class="control-label col-xs-2">IMEI Number</label>
            <div class="col-xs-5">
               <form:input path="imei" type="imei" class="form-control" id="inputIMEI" placeholder="Enter IMEI No"/>
            </div>
        </div> --%>
        
         <div class="form-group">
            <label for="inputSimCard" class="control-label col-xs-2">Sim Card Number</label>
            <div class="col-xs-5">
                 <form:input path="sim" type="simCardNo" class="form-control" id="inputSimCardNo" placeholder="Enter Sim Card No"/>
            </div>
        </div>
        
         <div class="form-group">
            <label for="inputSimCardProvider" class="control-label col-xs-2">Sim Card Provider</label>
            <div class="col-xs-5">
                <form:input path="provider" type="simCardProvider" class="form-control" id="inputSimCardProvider" placeholder="Enter Sim Card Provider"/>
            </div>
        </div>
      
      <div class="form-group">
            <label for="inputPhoneNumber" class="control-label col-xs-2">Phone Number</label>
            <div class="col-xs-5">
                <form:input path="mobile" type="phoneNumber" class="form-control" id="inputPhoneNumber" placeholder="Enter Phone Number"/>
            </div>
        </div>
        
        <div class="form-group"> 
                    <p class="center col-md-5">
                    <input type="submit" value="Test Tracker" title="Test Tracker." data-toggle="tooltip" data-placement="left" style="margin-left:5%"  class="btn btn-primary" id="myButton"/>
                       
                    </p>
        </div>
       
      </form:form>
      
    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!--/span-->
    
    
    
   
    
</div><!--/row-->

  
</div>
</div>
   
<%@include file="Footer.jsp" %>

</div>



<script src="bower_components/flot/excanvas.min.js"></script>
<script src="bower_components/flot/jquery.flot.js"></script>
<script src="bower_components/flot/jquery.flot.pie.js"></script>
<script src="bower_components/flot/jquery.flot.stack.js"></script>
<script src="bower_components/flot/jquery.flot.resize.js"></script>
<!-- chart libraries end -->
<script src="js/init-chart.js"></script>


<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- library for cookie management -->
<script src="js/jquery.cookie.js"></script>
<!-- calender plugin -->
<script src='bower_components/moment/min/moment.min.js'></script>
<script src='bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
<!-- data table plugin -->
<script src='js/jquery.dataTables.min.js'></script>

<!-- select or dropdown enhancer -->
<script src="bower_components/chosen/chosen.jquery.min.js"></script>
<!-- plugin for gallery image view -->
<script src="bower_components/colorbox/jquery.colorbox-min.js"></script>
<!-- notification plugin -->
<script src="js/jquery.noty.js"></script>
<!-- library for making tables responsive -->
<script src="bower_components/responsive-tables/responsive-tables.js"></script>
<!-- tour plugin -->
<script src="bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
<!-- star rating plugin -->
<script src="js/jquery.raty.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="js/jquery.iphone.toggle.js"></script>
<!-- autogrowing textarea plugin -->
<script src="js/jquery.autogrow-textarea.js"></script>
<!-- multiple file upload plugin -->
<script src="js/jquery.uploadify-3.1.min.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="js/jquery.history.js"></script>
<!-- application script for Charisma demo -->
<script src="js/charisma.js"></script>


</body>
</html>
