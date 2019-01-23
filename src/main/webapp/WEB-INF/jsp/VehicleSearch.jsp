<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
   
    <meta charset="utf-8">
    <title>C M T</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
   
	<!-- jQuery -->
    <script src="bower_components/jquery/jquery.min.js"></script>
    
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
	<script src="js/logistics.js"></script>

 <style>
      html, body, #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
      }
      .controls {
        margin-top: 16px;
        border: 1px solid transparent;
        border-radius: 2px 0 0 2px;
        box-sizing: border-box;
        -moz-box-sizing: border-box;
        height: 32px;
        outline: none;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
      }

      #pac-input {
        background-color: #fff;
        padding: 0 11px 0 13px;
        width: 400px;
        font-family: Roboto;
        font-size: 15px;
        font-weight: 300;
        text-overflow: ellipsis;
      }

      #pac-input:focus {
        border-color: #4d90fe;
        margin-left: -1px;
        padding-left: 14px;  /* Regular padding-left + 1. */
        width: 401px;
      }

      .pac-container {
        font-family: Roboto;
      }

      #type-selector {
        color: #fff;
        background-color: #4d90fe;
        padding: 5px 11px 0px 11px;
      }

      #type-selector label {
        font-family: Roboto;
        font-size: 13px;
        font-weight: 300;
      }
}

    </style>
    <title>Places search box</title>
    <script src="http://maps.google.com/maps/api/js?sensor=false"></script>
    
<script type="text/javascript">

       var markers = ${map.coordinates}
      
        window.onload = function () { 
        	 alert(markers.length);
           if(markers.length != 0){
        	 $("#map_section").show();	
        	   var mapOptions = {
                       center: new google.maps.LatLng(markers[0].latitude, markers[0].longitude),
                       zoom: 8,
                       mapTypeId: google.maps.MapTypeId.ROADMAP
                   };
                   var map = new google.maps.Map(document.getElementById("dvMap"), mapOptions);
                   var infoWindow = new google.maps.InfoWindow(); 
                   var lat_lng = new Array();
                   var latlngbounds = new google.maps.LatLngBounds();
                       var data = markers[0];
                       var myLatlng = new google.maps.LatLng(data.latitude, data.longitude);
                       lat_lng.push(myLatlng);
                       var image = 'img/marker.png';
                        var marker = new google.maps.Marker({
                           position: myLatlng,
                           map: map,
                           icon:image,
                           animation: google.maps.Animation.DROP,
                           title: data.address
                       });  
                     
                       latlngbounds.extend(myLatlng); 
                        (function (marker, data) {
                           google.maps.event.addListener(marker, "click", function (e) {
                               infoWindow.setContent(data.address);
                               infoWindow.open(map, marker);
                           });
                       })(marker, data); 
                  
                   map.setCenter(latlngbounds.getCenter());
                   map.fitBounds(latlngbounds);
                   google.maps.event.addListenerOnce(map, 'bounds_changed', function() {
                	   map.setZoom(14);
                	 });
           }
           
           
           
        }
        
        $(document).ready(function() { 
         $("#vehicleSearch").on('click', function(){
        var vehicleNumber = $("#vehicleNumber").val(); 
        if(vehicleNumber != ''){
        	document.location.href='/DevTracker/locateVehicle?vehicleNumber='+ vehicleNumber;
        }else{
        	 alert("Vehicle number cannot be empty!");
        }
   	 }); 
});
    </script>
    <style>
      #target {
        width: 345px;
      }
    </style> 
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
    
    </style>


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
            <a href="vehicleDetails">Search Vehicle</a>
        </li>
    </ul>
</div>

<div class="row">
   
    <!--/span-->

    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon glyphicon-user"></i> Search Vehicle</h2>

                <div class="box-icon">
                    <a href="#" class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>
                </div>
            </div>
           
                <div class="box-content">
                    <div class="bs-example">
    
    
        
         <div class="form-group">
            <label for="vehicleNumber" class="control-label col-xs-2" style="padding-top:7px;"> Vehicle Number </label>
            <div class="col-xs-4" >
                <input  type="text" class="form-control capsText" id="vehicleNumber" placeholder="Enter Vehicle Number"/>                 
            </div>
            <div class="col-xs-4" >
                <input type="submit" value="Vehicle Search" id="vehicleSearch" data-toggle="tooltip" data-placement="left" style="margin-right:25%"  class="btn btn-primary" />          
            </div>
        </div>       
        
        </br>
                           
    </div>
                </div>
            
            
            
            
            
        </div>
    </div>
    
    <!--/span-->
    
    
    
   
    
</div><!--/row-->


<div class="row">
   
    <!--/span-->

    <div class="box col-md-12">
        <div class="box-inner" style="display:none;" id="map_section">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon glyphicon-user"></i> Locate Vehicle</h2>

                <div class="box-icon">
                    <a href="#" class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>
                </div>
            </div>          
  
    <div id="dvMap" style="width: auto; height: 600px;" ></div>
 
        </div>
    </div>
</div>


  
</div>
</div>
   <%@include file="Footer.jsp" %>

</div>

	

</body>
</html>
