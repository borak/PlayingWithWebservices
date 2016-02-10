/**
 * Created by Kim on 2016-02-07.
 */

$(document).ready(function() {
    $('.menuButton').mouseenter(function() {
        $('.menuButton').fadeTo('fast', 1);
    });

    $('.menuButton').mouseleave(function() {
        $('.menuButton').fadeTo('fast', 0.7);
    });

    $('#testbtn').click(function(){$.ajax({
        url: "http://localhost:9998/helloworld-webapp/view/json",

        data: {
        },
        type: "GET",

        dataType : "text",

        success: function( text ) {
            weblog(text);
            console.log( "Success: " + text );
        },

        error: function( xhr, status, errorThrown ) {
            var error = "Error: " + errorThrown + "<br />" + "Status: " + status;
            weblog(error);
        },

        complete: function( xhr, status ) {
            var msg = "The request is complete!";
            weblog(msg + "<br />");
        }
    })});

	$('#test_customer_btn').click(function(){$.ajax({
        url: "http://localhost:9998/helloworld-webapp/view/json/",

        data: {
            id: 2
        },
        type: "GET",

        dataType : "json",

        success: function( json ) {
            weblog(json.firstName);
            console.log( "Success: " + json );
        },

        error: function( xhr, status, errorThrown ) {
            var error = "Error: " + errorThrown + "<br />" + "Status: " + status;
            weblog(error);
        },

        complete: function( xhr, status ) {
            var msg = "The request is complete!";
            weblog(msg + "<br />");
        }
    })});

});

function weblog(text) {
    $("#response_content").append(text + "<br />");
    $("#response_content").scrollTop($("#response_content").height);
}