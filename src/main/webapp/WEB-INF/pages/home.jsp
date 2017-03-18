<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="resources/js/library/jquery.min.js"></script>
<script src="resources/js/library/bootstrap.min.js"></script>
<script src="resources/js/library/angular.min.1.0.7.js"></script>
<script src="resources/js/person/controller.js"></script>
<script src="resources/js/person/service.js"></script>
<script src="resources/js/person/directive.js"></script>
<script src="resources/js/person/factory.js"></script>
<script src="resources/js/person/app.js"></script>
<script src="resources/js/library/jquery.maskedinput.min.js"></script>

	<script type="text/javascript">
		jQuery(function($){
			$( window ).load(function() {
				$("#phoneNumber").mask("(999)999-99-99",{placeholder:" "});
			});
		});
	</script>
<link rel="stylesheet"
	href="resources/css/library/bootstrap.min.css" type="text/css" />
<link rel="stylesheet"
	href="resources/css/main.css" type="text/css" />

<title>PersonApp</title>
</head>
<body ng-app="app">
	<div ng-controller="personController" class="tableField">

		<button ng-click="openSaveDialog()" class="btn btn-info" style="margin-bottom: 7px">
			Yeni Kayıt Ekle
		</button>
		
		<table class="table">
			<tr>
				<th>Ad</th>
				<th>Soyad</th>
				<th>Telefon</th>
				<th></th>
			</tr>
			<tr ng-repeat="person in persons">
				<td>{{person.name}}</td>
				<td>{{person.surname}}</td>
				<td>{{person.phoneNumber}}</td>
				<td>
					<button ng-click="openEditDialog(person)" class="btn btn-warning">
						Güncelle
					</button>
					<button ng-click="removePerson(person);" class="btn btn-danger">
						Sil
					</button>
				</td>
			</tr>
		</table>

		<modal title="PersonApp" visible="showModal">
			<div data-ng-show="showError" class="alert alert-danger">
				<ul ng-repeat="msg in errorMessages">
					<li>{{msg}}</li>
				</ul>
			</div>
			
			<form role="form">
				<div class="form-group">
					<label for="name">Adı</label>
					<input class="form-control" id="name" value="currentPerson.name" ng-model="currentPerson.name" />
				</div>
				<div class="form-group">
					<label for="surname">Soyadı</label>
					<input class="form-control" id="surname" value="currentPerson.surname" ng-model="currentPerson.surname" />
				</div>
				<div class="form-group">
					<label for="phoneNumber">Telefon</label>
					<input id="phoneNumber" class="form-control" type="text" placeholder="(xxx)xxx-xx-xx" value="currentPerson.phoneNumber" ng-model="currentPerson.phoneNumber" />
				</div>
				<div class="form-group" ng-show="dialogMode == 'save'">
					<div class="captcha">
						<label>
							{{captcha.generatedVal}}
						</label>
					</div>
					<label for="generatedCaptcha">Captcha değerini giriniz</label>
					<input class="form-control" id="captcha" value="captcha.val" ng-model="captcha.val" />
				</div>
				<button type="submit" class="btn btn-primary" ng-click="saveOrUpdatePerson()">
					{{getDialogButtonStr()}}
				</button>
			</form>
		</modal>
	</div>

	<div id="ajaxLoader">
		<img src="resources/img/ajax-loader.gif">
	</div>
</body>
</html>