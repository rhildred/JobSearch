jQuery(document).ready(function(){
	new JobSearch();
	Backbone.history.start();
});

var JobSearch = Backbone.Router.extend({
	initialize: function(){
		jQuery("#doSearch").submit(jQuery.proxy(this.doSearch, this));
	},
	doSearch: function(){
		this.navigate("doSearch/" + jQuery("#searchterm").val() + "/" + jQuery("#location").val(),
				{trigger: true});
		return false;
	},
	fLeadRow: _.template(jQuery("#leadrow").html()),
	displaySearchResults: function(oLeads){
		jQuery("#tablebody").html("");
		for(var n=0; n < oLeads.length; n++){
			jQuery("#tablebody").append(this.fLeadRow(oLeads[n]));
		}
	},
	displayError: function(err){
		console.log(err);
	},
	routes: {
		"doSearch/:sTerm/:sLocation": "getResults"
	},
	getResults: function(sTerm, sLocation){
		jQuery.ajax({url:"searchResults.jsp", dataType: "json", data: {term: sTerm, location: sLocation}})
		.done(jQuery.proxy(this.displaySearchResults, this))
		.error(jQuery.proxy(this.displayError, this) );
		
	}
});