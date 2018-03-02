$(document).ready(function(){
    init()
});
function init() {
    enableButtons();
    getAllStudents();
}
function enableButtons() {
    $("#btnSubmit1").on("click",addStudent);
    $("#btnSubmit2").on("click",findStudent);
}
function addStudent() {
    event.preventDefault();
    var formID=$("#idEntry").val();
    var formFName=$("#fNameEntry").val();
    var formLName=$("#lNameEntry").val();
    var formZip=$("#zipEntry").val();
    var newStudent= {
        idNum: formID,
        firstName: formFName,
        lastName: formLName,
        zipCode: formZip
    };
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        datatype: "json",
        data: JSON.stringify(newStudent),
        url: "add/student",
        success: function(serverData){
            getAllStudents()
        }
    });
}
function findStudent() {
    event.preventDefault();
    var searchID=$("#searchEntry").val();
    $.ajax({
        type: "GET",
        url: "/get/"+searchID,
        success: function (serverData) {
            appendStudent(serverData);
        }
    })
}
function appendAllStudents(studentArray) {
    $("#allStudentsList").empty();
    for(var i=0; i<studentArray.length; i++){
        var student=studentArray[i];
        $("#allStudentsList").append("<div></div>");
        var el = $("#allStudentsList").children().last();
        el.append("<p>" + student.idNum+" - "+
            student.firstName+" "+
            student.lastName+" "+
            student.zipCode+"</p>");
    }
}
function appendStudent(student) {
    $("#foundStudent").empty();
    $("#foundStudent").append("<div></div>");
    var el = $("#foundStudent").children().last();
    el.append("<p>" + student.idNum+" - "+
        student.firstName+" "+
        student.lastName+" "+
        student.zipCode+"</p>");
}
function getAllStudents() {
    $.ajax({
        type: "GET",
        url: "/getallstudents",
        success: function(serverData){
            appendAllStudents(serverData)
        }
    })
}
