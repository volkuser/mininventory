<#import "parts/common.ftl" as c>

<@c.page>
    <h3>Location control</h3>
    <div class="d-flex flex-row mb-3">
        <div class="p-2">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Number</th>
                        <th scope="col">Letter</th>
                        <th scope="col">Is auditory</th>
                        <th scope="col">Operating hours</th>
                        <th scope="col">Open from</th>
                    </tr>
                </thead>
                <tbody>
                    <#list locations as location>
                        <tr>
                            <td>${location.id}</td>
                            <td>${location.number}</td>
                            <td>${location.additionLetter}</td>
                            <td><#if location.isAuditory()>yes<#else>no</#if></td>
                            <td>${location.operatingHours}</td>
                            <td>${location.openFromAsString}</td>
                            <td><a href="location/more/${location.id}">More</a></td>
                        </tr>
                    <#else>
                        <td>no locations</td>
                    </#list>
                </tbody>
            </table>
        </div>
        <div class="d-flex flex-column mb-3">
            <div class="p-2">
                <form method="get">
                    <h5>Search</h5>
                    <div class="mb-3">
                        <label>
                            <input type="text" class="form-control" name="query"/>
                        </label>
                    </div>
                    <div class="p-1">
                        <button type="submit" class="btn btn-primary" formaction="/location/exact"
                                formmethod="get">Exact search</button>
                    </div>
                    <div class="p-1">
                        <button type="submit" class="btn btn-primary" formaction="/location/imprecise"
                                formmethod="get">Imprecise search</button>
                    </div>
                </form>
            </div>
            <div class="p-2">
                <h5>Add new location</h5>
                <form method="post">
                    <div class="mb-3 d-flex flex-row align-items-center">
                        <label class="form-label" for="number_id">Number</label>
                        <div class="p-2 d-flex flex-row">
                            <div class="d-flex flex-row">
                                <input id="number_id" type="number" value="1" name="number"
                                       class="form-control ${(numberError??)?string('is-invalid', '')} p-2">
                                <#if numberError??>
                                    <div class="invalid-feedback p-2">
                                        ${numberError}
                                    </div>
                                </#if>
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Addition letter</label>
                        <label>
                            <input type="text" class="form-control" maxlength="1" name="additionLetter"
                                   value="x">
                        </label>
                    </div>
                    <div class="mb-3 form-check">
                        <label>
                            <input type="checkbox" class="form-check-input" name="isAuditoryAsString"/>
                        </label>
                        <label class="form-check-label">Is auditory</label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Operation hours</label>
                        <label>
                            <input type="time" class="form-control" name="operatingHoursAsString"
                                   value="<#if operatingHoursAsString??>${selectedLocation.operatingHours}</#if>">
                        </label>
                    </div>
                    <div class="mb-3 d-flex flex-row align-items-center">
                        <label class="form-label" for="openFrom_id">Open from</label>
                        <div class="p-2 d-flex flex-row">
                            <div class="d-flex flex-row">
                                <input id="openFrom_id" type="date" name="openFrom"
                                       class="form-control ${(openFromError??)?string('is-invalid', '')} p-2">
                                <#if openFromError??>
                                    <div class="invalid-feedback p-2">
                                        ${openFromError}
                                    </div>
                                </#if>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Add</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>