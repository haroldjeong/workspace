<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/jsp/include/taglib.jsp"/>

<div class="row">
    <div class="col-lg-12">
        <div class="ibox ">
            <div class="ibox-title">
                <h2>table</h2>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="input-group">
                            <input placeholder="Search" type="text" class="form-control form-control-sm">
                            <span class="input-group-append">
                                <button type="button" class="btn btn-sm btn-primary">Go!</button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th></th>
                            <th>Project </th>
                            <th>Completed </th>
                            <th>Task</th>
                            <th>Date</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input type="checkbox"  checked class="i-checks" name="input[]"></td>
                            <td>Project<small>This is example of project</small></td>
                            <td><span class="pie">0.52/1.561</span></td>
                            <td>20%</td>
                            <td>Jul 14, 2013</td>
                            <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" class="i-checks" name="input[]"></td>
                            <td>Alpha project</td>
                            <td><span class="pie">6,9</span></td>
                            <td>40%</td>
                            <td>Jul 16, 2013</td>
                            <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" class="i-checks" name="input[]"></td>
                            <td>Betha project</td>
                            <td><span class="pie">3,1</span></td>
                            <td>75%</td>
                            <td>Jul 18, 2013</td>
                            <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" class="i-checks" name="input[]"></td>
                            <td>Gamma project</td>
                            <td><span class="pie">4,9</span></td>
                            <td>18%</td>
                            <td>Jul 22, 2013</td>
                            <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="tabs-container">
            <ul class="nav nav-tabs" role="tablist">
                <li><a class="nav-link active" data-toggle="tab" href="#tab-1"> This is tab</a></li>
                <li><a class="nav-link" data-toggle="tab" href="#tab-2">This is second tab</a></li>
            </ul>
            <div class="tab-content">
                <div role="tabpanel" id="tab-1" class="tab-pane active">
                    <div class="panel-body">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th></th>
                                <th>Second Project</th>
                                <th>Completed </th>
                                <th>Task</th>
                                <th>Date</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><input type="checkbox"  checked class="i-checks" name="input[]"></td>
                                <td>Project<small>This is example of project</small></td>
                                <td><span class="pie">0.52/1.561</span></td>
                                <td>20%</td>
                                <td>Jul 14, 2013</td>
                                <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div role="tabpanel" id="tab-2" class="tab-pane">
                    <div class="panel-body">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th></th>
                                <th>Project </th>
                                <th>Completed </th>
                                <th>Task</th>
                                <th>Date</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><input type="checkbox"  checked class="i-checks" name="input[]"></td>
                                <td>Project<small>This is example of project</small></td>
                                <td><span class="pie">0.52/1.561</span></td>
                                <td>20%</td>
                                <td>Jul 14, 2013</td>
                                <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>