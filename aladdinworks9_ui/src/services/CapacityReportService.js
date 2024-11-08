import http from "../http-common"; 

class CapacityReportService {
  getAllCapacityReports(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/capacityReport/capacityReports`, searchDTO);
  }

  get(capacityReportId) {
    return this.getRequest(`/capacityReport/${capacityReportId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/capacityReport?field=${matchData}`, null);
  }

  addCapacityReport(data) {
    return http.post("/capacityReport/addCapacityReport", data);
  }

  update(data) {
  	return http.post("/capacityReport/updateCapacityReport", data);
  }
  
  uploadImage(data,capacityReportId) {
  	return http.postForm("/capacityReport/uploadImage/"+capacityReportId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new CapacityReportService();
