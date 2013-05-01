var errorsGeneral = {
	ERROR_PAGENAME_LENGTH:'Web page name length must be between 1 and 250 characters ',
	ERROR_PAGE_INVALID_TYPE: 'Invalid operation type',
	ERROR_PAGE_BAD_FORMAT: 'Invalid format for page name: allowed characters are 0-9, a-z, A-Z, -, _,. (, is not an allowed character)'
	
};

$().ready( function () {
	var wbPageValidations = { 
			name: [{rule: { rangeLength: { 'min': 1, 'max': 250 } }, error: "ERROR_PAGENAME_LENGTH" }, {rule:{customRegexp:{pattern:"^[0-9a-zA-Z_.-]*$", modifiers:"gi"}}, error:"ERROR_PAGE_BAD_FORMAT"}],
			isTemplateSource: [{rule: { includedInto: ['0','1'] }, error: "ERROR_PAGE_INVALID_TYPE" }]
	};

	$('#wbPageEditForm').wbObjectManager( { fieldsPrefix:'wbe',
									  errorLabelsPrefix: 'erre',
									  errorGeneral:"errageneral",
									  errorLabelClassName: 'errorvalidationlabel',
									  errorInputClassName: 'errorvalidationinput',
									  fieldsDefaults: { isTemplateSource: 0 },
									  validationRules: wbPageValidations
									 });

	var displayHandler = function (fieldId, record) {
		if (fieldId == 'lastModified') {
			var date = new Date();
			return date.toFormatString(record[fieldId], "dd/mm/yyyy hh:mm:ss");
		} 
		if (fieldId == 'name') {
			var innerHtml = '<a href="./webpage.html?key=' + encodeURIComponent(record['key']) + '">' + escapehtml(record['name']) + '</a>';
			return innerHtml;
		}

		return record[fieldId];
	}
	$('#wbPageSummary').wbDisplayObject( { fieldsPrefix: 'wbsummary', customHandler: displayHandler} );
	
	var fSuccessGetPage = function (data) {
		$('#wbPageSummary').wbDisplayObject().display(data);
		$('#wbPageEditForm').wbObjectManager().populateFieldsFromObject(data);
	}
	
	var fErrorGetPage = function (errors, data) {
		alert(errors);
	}

	var pageKey = getURLParameter('key'); 
	var externalKey = getURLParameter('externalKey');
	$('#wbEditPageForm').wbCommunicationManager().ajax ( { url:"./wbpage/" + encodeURIComponent(pageKey),
												 httpOperation:"GET", 
												 payloadData:"",
												 functionSuccess: fSuccessGetPage,
												 functionError: fErrorGetPage
												} );
	
	var fSuccessEdit = function ( data ) {
		window.location.href = "./webpage.html?key=" + encodeURIComponent(pageKey) + "&externalKey=" + encodeURIComponent(externalKey);
	}
	var fErrorEdit = function (errors, data) {
		$('#wbEditPageForm').wbObjectManager().setErrors(errors);
	}

	$('.wbPageEditSaveBtnClass').click( function (e) {
		e.preventDefault();
		var errors = $('#wbPageEditForm').wbObjectManager().validateFieldsAndSetLabels( errorsGeneral );
		if ($.isEmptyObject(errors)) {
			var page = $('#wbPageEditForm').wbObjectManager().getObjectFromFields();
			var jsonText = JSON.stringify(page);
			$('#wbPageEditForm').wbCommunicationManager().ajax ( { url: "./wbpage/" + encodeURIComponent(pageKey),
															 httpOperation:"PUT", 
															 payloadData:jsonText,
															 wbObjectManager : $('#wbEditPageForm').wbObjectManager(),
															 functionSuccess: fSuccessEdit,
															 functionError: fErrorEdit
															 } );
		}
	});
	
	$('.wbPageEditCancelBtnClass').click ( function (e) {
		e.preventDefault();
		window.location.href = "./webpage.html?key=" + encodeURIComponent(pageKey) + "&externalKey=" + encodeURIComponent(externalKey);
	});


													
});