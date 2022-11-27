<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Edit location #${selectedLocation.id}</h5>
    <form method="post">
        <div class="mb-3 d-flex flex-row align-items-center">
            <label class="form-label" for="number_id">Number</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <input id="number_id" type="number" value="${selectedLocation.number!'1'}" name="number"
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
                <input type="text" class="form-control" maxlength="1" value="${selectedLocation.additionLetter}"
                    name="additionLetter">
            </label>
        </div>
        <div class="mb-3 form-check">
            <label>
                <#if selectedLocation.isAuditory()>
                    <input type="checkbox" class="form-check-input" name="isAuditoryAsString" checked>
                <#else>
                    <input type="checkbox" class="form-check-input" name="isAuditoryAsString">
                </#if>
            </label>
            <label class="form-check-label">Is auditory</label>
        </div>
        <div class="mb-3">
            <label class="form-label">Operation hours</label>
            <label>
                <input type="time" class="form-control" name="operatingHoursAsString"
                    value="${selectedLocation.operatingHours}">
            </label>
        </div>
        <div class="mb-3 d-flex flex-row align-items-center">
            <label class="form-label" for="openFrom_id">Open from</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <input id="openFrom_id" type="date" value="${openFrom}" name="openFrom"
                           class="form-control ${(openFromError??)?string('is-invalid', '')} p-2">
                    <#if openFromError??>
                        <div class="invalid-feedback p-2">
                            ${openFromError}
                        </div>
                    </#if>
                </div>
            </div>
        </div>
        <a type="button" class="btn btn-primary" href="/location">Back</a>
        <button type="submit" formmethod="post" class="btn btn-primary">Update</button>
        <button type="submit" class="btn btn-primary"
                formmethod="post" formaction="/location/more/${selectedLocation.id}/delete">Delete</button>
    </form>
</@c.page>