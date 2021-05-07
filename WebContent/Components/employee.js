$(document).ready(function(){
	$("#alertSuccess").hide();
	$("#alertError").hide();
}); 

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateEmployeeForm();
	if(status != true)
		{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
		}
	
// If valid------------------------
	
	//$("#formEmployee").submit
	var type = ($("#hidEmployeeIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "employeeAPI",
		type : type,
		data : $("#formEmployee").serialize(),
		dataType : "text",
		complete : function(response, status) 
		{
			onEmployeeSaveCompelet(response.responseText, status);
		}
	});
});
	
	function onEmployeeSaveCompelet(response, status) {
		if (status == "success") 
		{
			var resultSet = JSON.parse(response);
			
			if (resultSet.status.trim() == "success") 
			{
				$("#alertSuccess").text("Successfully saved.");
				$("#alertSuccess").show();
				
				$("#divEmployeeGrid").html(resultSet.data);
				
			} else if (resultSet.status.trim() == "error") {
				
				$("#alertError").text(resultSet.data);
				$("#alertError").show();
			}
		} else if (status == "error") {
			$("#alertError").text("Error while saving.");
			$("#alertError").show();
		} else {
			$("#alertError").text("Unknown error while saving..");
			$("#alertError").show();
		}
		$("#hidEmployeeIDSave").val("");
		$("#formEmployee")[0].reset();
	}
	



//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
		{
			$("#hidEmployeeIDSave").val($(this).closest("tr").find('#hidEmployeeIDUpdate').val());
			$("#employeeName").val($(this).closest("tr").find('td:eq(0)').text());
			$("#employeeAddress").val($(this).closest("tr").find('td:eq(1)').text());
			$("#employeeContact").val($(this).closest("tr").find('td:eq(2)').text());
			$("#employeeDob").val($(this).closest("tr").find('td:eq(3)').text());
		});


//remove
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "employeeAPI",
		type : "DELETE",
		data : "employeeId=" + $(this).data("employeeid"),
		dataType : "text",
		complete : function(response, status) 
		{
			onEmployeeDeleteComplete(response.responseText, status);
		}
	});
});


function onEmployeeDeleteComplete(response, status) {
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") 
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			
			$("#divEmployeeGrid").html(resultSet.data);
		
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

//CLIENTMODEL=========================================================================
function validateEmployeeForm() {

	if ($("#employeeName").val().trim() == "") {
		return "Insert Employee Name.";
	}

	if ($("#employeeAddress").val().trim() == "") {
		return "Insert Employee Address.";
	}

	if ($("#employeeContact").val().trim() == "") {
		return "Insert Employee Contact.";
	}

	if ($("#employeeDob").val().trim() == "") {
		return "Insert Employee Date of Birth.";
	}


	
	return true;
}


