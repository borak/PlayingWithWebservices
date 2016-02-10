/**
 * Created by Kim on 2016-02-07.
 */
/**
 · Hur du kan skapa och manipulera html element “i runtime”.
 · Kommunicera med webservicar med hjälp av jQuerys ajax funktionalitet. T.ex. hämta data från en webservice och visa
 det genom att bygga upp en tabell i html med hjälp av jQuery utan att ladda om sidan när man trycker på en knapp.
 · Events
 · Deferred objects
 */

$(document).ready(function() {
    $('.menuButton').mouseenter(function() {
        $('.menuButton').fadeTo('fast', 1);
    });

    $('.menuButton').mouseleave(function() {
        $('.menuButton').fadeTo('fast', 0.7);
    });

    $('#testbtn').click(function(){$.ajax({

        // The URL for the request
        url: "http://localhost:9998/helloworldserver/",

        // The data to send (will be converted to a query string)
        data: {
            //    id: 123
        },

        // Whether this is a POST or GET request
        type: "GET",

        // The type of data we expect back
        //dataType : "json",
        dataType : "text",

        // Code to run if the request succeeds;
        // the response is passed to the function
        success: function( json ) {
            //$( "<h1>" ).text( json.title ).appendTo( "body" );
            //$( "<div class=\"content\">").html( json.html ).appendTo( "body" );
            //$("#response_content").append(json + "<br />");
            weblog(json);
            console.log( "Success: " + json );
        },

        // Code to run if the request fails; the raw request and
        // status codes are passed to the function
        error: function( xhr, status, errorThrown ) {
            var error = "Error: " + errorThrown + "<br />" + "Status: " + status;
            //$("#response_content").append(error);
            weblog(error);
        },

        // Code to run regardless of success or failure
        complete: function( xhr, status ) {
            var msg = "The request is complete!";
            weblog(msg + "<br />");
        }
    })});

});

function weblog(text) {
    $("#response_content").append(text + "<br />");
    //var nextinside = $(this).parent().parent('.inside').nextAll(".inside:first");
    //$("#response_content").animate({scrollTop: nextinside.index() * nextinside.offset().top}, 200);
    $("#response_content").scrollTop($("#response_content").height);
    //$("#response_content").scrollTop($("#response_content").offset().top);
}