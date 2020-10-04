<head>
    <#include "../fragments/bootsrap-links.ftl"/>
    <link rel="stylesheet" href="css/stylesheet.css">
    <#--    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">-->
    <#--    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>-->
    <#--    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>-->
</head>


<body>
<#include "../fragments/nav-bar.ftl" />
<br>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet'
      type='text/css'>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">

            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-6">
                            <h2 class="text-danger">Admission Requests </h2>
                        </div>
<#--                        <div class="col col-xs-6 text-right">-->
<#--                            <button type="button" class="btn btn-sm btn-primary btn-create">Create New</button>-->
<#--                        </div>-->
                    </div>
                </div>
                <div class="panel-body">
                    <table class="table table-striped table-bordered table-list">
                        <thead>
                        <tr>

                            <th>ID</th>
                            <th>From Candidate</th>
                            <th>To the Faculty</th>
                            <th>Status</th>
                            <th>Creation date and time</th>


                            <th align="center">Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list requests_list as request>
                            <tr>

                                <td>${request.getId()}</td>
                                <td>${request.candidate.getUsername()}</td>
                                <td>${request.faculty.getName()}</td>
                                <td>${request.status.name()}</td>
                                <td>
                                    ${request.getCreationDateTime()}
                                </td>

                                <td align="center">
                                    <form
                                            action="/candidate/delete_request/${request.getId()}" method="post">
                                        <button class="btn btn-primary" type="submit">Delete request</button>
                                    </form>

                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>

                </div>
                <div class="panel-footer">
                    <div class="row">
                        <div class="col col-xs-4">Page 1 of 5
                        </div>
                        <div class="col col-xs-8">
                            <ul class="pagination hidden-xs pull-right">
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                            </ul>
                            <ul class="pagination visible-xs pull-right">
                                <li><a href="#">«</a></li>
                                <li><a href="#">»</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


</body>