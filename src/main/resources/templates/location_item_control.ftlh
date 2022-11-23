<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Edit location #${selectedLocation.id}</h5>
    <form method="post">
        <div class="mb-3">
            <label class="form-label">Number</label>
            <label>
                <input type="number" class="form-control" name="number" min="1"
                       value="${selectedLocation.number!'1'}">
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">Addition letter</label>
            <label>
                <input type="text" class="form-control" maxlength="1" name="additionLetter"
                       value="${selectedLocation.additionLetter}">
            </label>
        </div>
        <div class="mb-3 form-check">
            <label>
                <#if selectedLocation.isAuditory()>
                    <input type="checkbox" class="form-check-input" name="isAuditory" checked>
                <#else>
                    <input type="checkbox" class="form-check-input" name="isAuditory">
                </#if>
            </label>
            <label class="form-check-label">Is auditory</label>
        </div>
        <div class="mb-3">
            <label class="form-label">Operation hours</label>
            <label>
                <input type="time" class="form-control" name="operatingHours"
                       value="${selectedLocation.operatingHours}">
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">Open from</label>
            <label>
                <input type="date" class="form-control" name="openFrom"
                       value="${openFrom}">
            </label>
        </div>
        <a type="button" class="btn btn-primary" href="/location">Back</a>
        <button type="submit" formmethod="post" class="btn btn-primary">Update</button>
        <button type="submit" class="btn btn-primary">Delete</button>
    </form>
</@c.page>