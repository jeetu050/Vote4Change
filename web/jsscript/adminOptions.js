
function showupdatecandidateform(){
    removecandidateForm();
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red");
    newdiv.innerHTML="<h3>Update Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML+"<div style='color:white; font-weight:bold;'>Candidate Id : </div><div><select id='cid'></select></div>"
    
    newdiv.innerHTML = newdiv.innerHTML +"<br><div id='candidateform'><span id = 'addresp' style='color:white; font-weight:bold;'></span></div>"
    var addcand=$("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn(3500);
    
    data={data:"cid"};

    $.post("UpdateCandidateControllerServlet",data,function(responseText){
        
        var candidateIdList = JSON.parse(responseText);
       
        $("#cid").append(candidateIdList.cid);
        
    });
    
    $("#cid").change(function(){
        var cid = $("#cid").val();
        if(cid === ''){
            swal("No Selection!", "Please Select an ID","error");
            return;
        }
        data={data:cid};
    $.post("UpdateCandidateControllerServlet",data,function(responseText){
        clearText();

        var details = JSON.parse(responseText);
        $("#addresp").append(details.subdetails);
        
    });
        
        
    });

}

function showaddcandidateform()
{
removecandidateForm();
var newdiv=document.createElement("div");
newdiv.setAttribute("id","candidateform");
newdiv.setAttribute("float","left");
newdiv.setAttribute("padding-left","12px");
newdiv.setAttribute("border","solid 2px red");
newdiv.innerHTML="<h3>Add New Candidate</h3>";
newdiv.innerHTML=newdiv.innerHTML+"<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>\n\
<table><tr><th>Candidate Id:</th><td><input type='text' id='cid'></td></tr>\n\
<tr><th>User Id:</th><td><input type='text' id='uid' onkeypress='return getdetails(event)'></td></tr>\n\
<tr><th>Candidate Name:</th><td><input type='text' id='cname'></td></tr>\n\
<tr><th>City:</th><td><select id='city'></select></td></tr>\n\
<tr><th>Party:</th><td><input type='text' id='party'></td></tr>\n\
<tr><td colspan='2'><input type='file' name='files' value='Select Image'></td></tr>\n\
<tr><th><input type='button' value='Add Candidate' onclick='addcandidate()' id='addcnd'></th>\n\
<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>";
newdiv.innerHTML=newdiv.innerHTML+"<br><span id='addresp'></span>";
var addcand=$("#result")[0];
addcand.appendChild(newdiv);
$("#candidateform").hide();
$("#candidateform").fadeIn(3500);
 data={id:"getid"};
    $.post("AddCandidateControllerServlet",data,function(responseText){
        $("#cid").val(responseText);
        $('#cid').prop("disabled",true);
    });

}




function redirectadministratorpage(){
    swal("Admin!","Redirecting to Admin actions page" , "success").then(value=>{
       window:location = "adminactions.jsp" 
    });
}
function redirectResultPage(){
    swal("Admin!","Redirecting to Results actions page" , "success").then(value=>{
       window:location = "resultActions.jsp" 
    });
}
function redirectvotingpage(){
    swal("Admin!","Redirecting to Voting Controller page" , "success").then(value=>{
       window:location = "VotingControllerServlet" 
    });
}
function manageuser(){
    swal("Admin!","Redirecting to User Management page" , "success").then(value=>{
       window:location = "manageuser.jsp" 
    });
}

function managecandidate(){
    swal("Admin!","Redirecting to Candidate Management page" , "success").then(value=>{
       window:location = "managecandidate.jsp" 
    });
}


function getdetails(e){
    if(e.keyCode == 13)
    {
        data = {uid : $("#uid").val()};
        $.post("AddCandidateControllerServlet" , data , function(responseText){
            
            var details = JSON.parse(responseText);
            var city = details.city;
            var uname = details.username;
            if(uname === "Wrong")
            {
                swal("Wrong Adhar No!" , "Adhar No is Invalid!" , "error");
            }
            else
            {
                $("#cname").val(uname);
                $("#city").empty();
                $("#city").append(city);
                $("#cname").prop("disabled",true);
            }
            
        });
    }
}
function updateCandidate(){
    
    var form = $("#fileUploadForm")[0];
    
    var data = new FormData(form);
    var cid = $("#cid").val();
    var city = $("#city").val();
    var party = $("#party").val();
    data.append("city",city);
    data.append("party",party);
    data.append("cid",cid);
    
    
    
    $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "UpdateNewCandidateControllerServlet",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                str=data+"....";
                 swal("Admin!", str, "success").then(value => showupdatecandidateform()
    );

            },
            
        error: function (e) {
                swal("Admin!", e, "error");
                }
        });
    
}

function addcandidate()
{
    var form = $("#fileUploadForm")[0];
    var data = new FormData(form);
    
    var cid = $("#cid").val();
    var cname = $("#cname").val();
    var city = $("#city").val();
    var party = $("#party").val();
    var uid = $("#uid").val();
    data.append("cid",cid);
    data.append("uid",uid);
    data.append("cname",cname);
        data.append("party",party);
    data.append("city",city);
    
    $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "AddNewCandidateControllerServlet",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                
                
                
                    str=data+"...."; 
                
                    
                
                
                
                swal("Admin!", str, "success").then(value => showaddcandidateform()
    );

            },
            
        error: function (e) {
                swal("Admin!", e, "error");
                }
        });


    
}

function removecandidateForm()
{
    var contdiv=document.getElementById("result");
    var formdiv=document.getElementById("candidateform");
    if(formdiv!==null)
    {
        $("#candidateform").fadeOut("3500");
        contdiv.removeChild(formdiv);   
    }
}

function deleteChoosenCandidate(){
    var userid = $("#cid").val();
    data={cid:userid};
    $.post("DeleteChoosenCandidateControllerServlet", data, function(responseText){
        
        if(responseText.trim() == "success"){
            swal("Success" , "Candidate Deleted Successfully!", "success").then(value => deletecandidate()
    );
        }
        else{
            swal("Failed", "Sorry Something went wrong Please try Again!","error").then(value => deletecandidate()
    );
        }
    });
}

function deleteCandidate(){
    
        removecandidateForm();
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red");
    newdiv.innerHTML="<h3>Delete Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML+"<div style='color:white; font-weight:bold;'>Candidate Id : </div><div><select id='cid'></select></div>"

    newdiv.innerHTML = newdiv.innerHTML +"<br><span id = 'addresp'></span>"
    var addcand=$("#result")[0];
addcand.appendChild(newdiv);
$("#candidateform").hide();
$("#candidateform").fadeIn(3500);

 data={data:"cid"};

    $.post("DeleteCandidateControllerServlet",data,function(responseText){
        
        var candidateIdList = JSON.parse(responseText);
        
        $("#cid").append(candidateIdList.cid);
        
    });
    $("#cid").change(function(){
        var cid = $("#cid").val();
        if(cid === ''){
            swal("No Selection!", "Please Select an ID","error");
            return;
        }
        data={data:cid};
    $.post("DeleteCandidateControllerServlet",data,function(responseText){
        clearText();
        
        var details = JSON.parse(responseText);
        $("#addresp").append(details.subdetails);
        
    });
        
        
    });
}

function showcandidate(){
    
    removecandidateForm();
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red");
    newdiv.innerHTML="<h3>Show Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML+"<div style='color:white; font-weight:bold;'>Candidate Id : </div><div><select id='cid'></select></div>"

    newdiv.innerHTML = newdiv.innerHTML +"<br><span id = 'addresp'></span>"
    var addcand=$("#result")[0];
addcand.appendChild(newdiv);
$("#candidateform").hide();
$("#candidateform").fadeIn(3500);

 data={data:"cid"};

    $.post("ShowCandidateControllerServlet",data,function(responseText){
        
        var candidateIdList = JSON.parse(responseText);
        
        $("#cid").append(candidateIdList.cid);
        
    });
    $("#cid").change(function(){
        var cid = $("#cid").val();
        if(cid === ''){
            swal("No Selection!", "Please Select an ID","error");
            return;
        }
        data={data:cid};
    $.post("ShowCandidateControllerServlet",data,function(responseText){
        clearText();
        
        var details = JSON.parse(responseText);
        $("#addresp").append(details.subdetails);
        
    });
        
        
    });
    }
    function clearText(){
        $("#addresp").html("");
    }
    
    function electionresult(){
        clearText();
        data = {data:"candidate"};
        $.post("ElectionResultControllerServlet", data,function(responseText){
            swal("Result Fetched!","Success","success");
            $("#result").html(responseText.trim());
        });
    }
    function electionresultBasedOnParty(){
        clearText();
        data = {data:"party"};
        $.post("ElectionResultControllerServlet", data,function(responseText){
            swal("Result Fetched!","Success","success");
            $("#result").html(responseText.trim());
        });
    }
    function electionresultBasedOnGender(){
        clearText();
        data = {data:"gender"};
        $.post("ElectionResultControllerServlet", data,function(responseText){
            swal("Result Fetched!","Success","success");
            $("#result").html(responseText.trim());
        });
    }
    
    function showUsersForm(){
        
        $.post("ShowUserDetailsServlet",null,function(responseText){
            swal("User Details","Success","success");
            $("#result").html(responseText.trim());
        });
    }
    function showDeleteUsersForm(){
        removecandidateForm();
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red");
    newdiv.innerHTML="<h3>Remove Users</h3>";
    newdiv.innerHTML = newdiv.innerHTML+"<div style='color:white; font-weight:bold;'>User Id : </div><div><select id='uid'></select></div>"

    newdiv.innerHTML = newdiv.innerHTML +"<br><span id = 'addresp'></span>"
    var addcand=$("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn(3500);
        
        data = {userId:"userId"};
        $.post("DeleteUserControllerServlet",data,function(responseText){
            
            $("#uid").append(responseText.trim());
        });
        
        $("#uid").change(function(){
        var uid = $("#uid").val();
        if(uid === ''){
            swal("No Selection!", "Please Select an ID","error");
            return;
        }
        data={userId:uid};
    $.post("DeleteUserControllerServlet",data,function(responseText){
        clearText();
        $("#addresp").append(responseText.trim());
        
    });
        
        
    });
        
    }
 function deleteChoosenUser(){
     var uid = $("#uid").val();
     data = {userId:uid};
     $.post("DeleteChoosenUserControllerServlet",data,function(responseText){
        clearText();
        if(responseText.trim() == "success")
        {
            swal("Success","User Deleted Successfully!","success").then( 
                    value => showDeleteUsersForm()
                    );
        }
        else
        {
            swal("Error","Sorry Something went wrong\n User not Deleted","error").then( 
                    value => showDeleteUsersForm()
                    );
        }
        
    });
 }




