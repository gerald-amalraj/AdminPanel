 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <!-- left menu starts -->
        <div class="col-sm-3 col-lg-2">
            <div class="sidebar-nav">
                <div class="nav-canvas">
                    <div class="nav-sm nav nav-stacked">
					
                    </div>
                    <ul class="nav nav-pills nav-stacked main-menu">
                        <c:if test="${role_id == 1}">
                        <li><a class="ajax-link" href="trackerDetails"><i class="glyphicon glyphicon-eye-open"></i><span> Tracker Details</span></a></li>
                        <li><a class="ajax-link" href="trackerConfigure"><i class="glyphicon glyphicon-edit"></i><span> Tracker Configure</span></a></li>
                        </c:if>
                        <c:if test="${role_id == 1 || role_id == 6 || role_id == 7}">
                        <li><a class="ajax-link" href="vehicleDetails"><i class="glyphicon glyphicon-edit"></i><span> Vehicle Details</span></a></li> 
                        </c:if>
                        <c:if test="${role_id == 1 || role_id == 2 || role_id == 6 || role_id == 7}">
                        <li><a class="ajax-link" href="assignVehicle"><i class="glyphicon glyphicon-list-alt"></i><span> Assign Vehicle</span></a></li>
                        </c:if>
                        <c:if test="${role_id == 1 || role_id == 2 || role_id == 3 || role_id == 6 || role_id == 7}">
                        <li><a class="ajax-link" href="driverDetails"><i class="glyphicon glyphicon-font"></i><span> Driver Details</span></a></li>
                        </c:if>
                        <c:if test="${role_id == 1 || role_id == 2 || role_id == 6}">
                        <li><a class="ajax-link" href="customerReport"><i class="glyphicon glyphicon-magnet"></i><span> Client Report</span></a></li>
                        </c:if>
                        <c:if test="${role_id == 1 || role_id == 6}">
                        <li><a class="ajax-link" href="vehicleReport"><i class="glyphicon glyphicon-certificate"></i><span> Vehicle Report</span></a></li>
                        </c:if>
                        <c:if test="${role_id == 1}">
                        <li><a class="ajax-link" href="trackerReport"><i class=" glyphicon glyphicon-road"></i><span> Tracker Report</span></a></li>
                        </c:if>
                        <c:if test="${role_id == 1 || role_id == 6}">
                        <li><a class="ajax-link" href="loginReport"><i class=" glyphicon glyphicon-eye-open"></i><span> Login Report</span></a></li>  
                        </c:if>
                        <c:if test="${role_id == 1 || role_id == 2 || role_id == 6}">
                        <li><a class="ajax-link" href="CreateLogin"><i class="glyphicon glyphicon-leaf"></i><span> Create Login</span></a></li> 
                        </c:if>
                        <c:if test="${role_id == 1 || role_id == 4}">
                        <li><a class="ajax-link" href="agent?id=${handler_id}"><i class="glyphicon glyphicon-leaf"></i><span> CF Agent</span></a></li>  
                        </c:if>                        
                        <c:if test="${role_id == 5}">
                        <li><a class="ajax-link" href="linkAgent"><i class="glyphicon glyphicon-leaf"></i><span> Link Agent</span></a></li>
                        <li><a class="ajax-link" href="deliveryAddress?id=${handler_id}"><i class="glyphicon glyphicon-leaf"></i><span> Delivery Address</span></a></li>
                        <li><a class="ajax-link" href="trackReport?id=${handler_id}"><i class="glyphicon glyphicon-leaf"></i><span> Track Report</span></a></li>  
                        </c:if>
                        <c:if test="${role_id == 1 || role_id == 6}">
                        <li><a class="ajax-link" href="vehicleSearch"><i class="glyphicon glyphicon-eye-open"></i><span> Search Vechicle</span></a></li>      
                        </c:if>                                               
                     </ul>
                </div>
            </div>
        </div>
        <!-- left menu ends -->