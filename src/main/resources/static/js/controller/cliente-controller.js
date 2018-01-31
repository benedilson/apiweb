// Criação de controllers
appCliente.controller("clienteController", function ($scope, $http){
	
	$scope.clientes=[];
	$scope.cliente={};
	
	carregarClientes = function () {
		
		token = localStorage.getItem("userToken");
		
	  //$http.defaults.headers.common.Authorization = 'Bearer '+ token;
		
		$http({method:'GET', url:'/administrator/clientes'})
		.then(function (response){
			$scope.clientes = response.data;
			
		}, function (response){
			console.log(response.data);
			console.log(response.status);
		});
	};
	
	$scope.salvarCliente = function () {
		$http({method:'POST', url:'/administrator/clientes', data:$scope.cliente})
		.then(function (response){
			//$scope.clientes.push(response.data);
			carregarClientes();
			$scope.cancelarAlteracaoCliente();
			$scope.frmCliente.$setPristine(true);
			
		}, function (response){
			console.log(response.data);
			console.log(response.status);
		});
	};
	
	$scope.excluirCliente = function (cliente) {
		$http({method:'DELETE', url:'/administrator/clientes/'+cliente.id})
		.then(function (response){
			
			pos = $scope.clientes.indexOf(cliente);
			$scope.clientes.splice(pos, 1);
			
		}, function (response){
			console.log(response.data);
			console.log(response.status);
		});
	};
	
	$scope.alterarCliente = function (cli) {
		$scope.cliente = angular.copy(cli);
	};
	
	$scope.cancelarAlteracaoCliente = function () {
		$scope.cliente = {};
	};
	
	carregarClientes();
});