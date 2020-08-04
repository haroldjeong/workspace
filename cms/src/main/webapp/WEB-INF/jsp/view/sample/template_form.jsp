<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/jsp/include/taglib.jsp"/>

<div class="row">
    <div class="col-lg-12">
        <div class="ibox ">
            <div class="ibox-title">
                <h5>All form elements <small>With custom checbox and radion elements.</small></h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-wrench"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#" class="dropdown-item">Config option 1</a>
                        </li>
                        <li><a href="#" class="dropdown-item">Config option 2</a>
                        </li>
                    </ul>
                    <a class="close-link">
                        <i class="fa fa-times"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">
                <form method="get">
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Normal</label>
                        <div class="col-sm-10"><input type="text" class="form-control"></div>
                    </div>
                    <div class="hr-line-dashed"></div>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Normal</label>
                        <div class="col-sm-4"><input type="text" class="form-control"></div>
                        <label class="col-sm-2 col-form-label">Normal</label>
                        <div class="col-sm-4"><input type="text" class="form-control"></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>