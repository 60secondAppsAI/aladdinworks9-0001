import http from "../http-common"; 

class EnergyConsumptionService {
  getAllEnergyConsumptions(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/energyConsumption/energyConsumptions`, searchDTO);
  }

  get(energyConsumptionId) {
    return this.getRequest(`/energyConsumption/${energyConsumptionId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/energyConsumption?field=${matchData}`, null);
  }

  addEnergyConsumption(data) {
    return http.post("/energyConsumption/addEnergyConsumption", data);
  }

  update(data) {
  	return http.post("/energyConsumption/updateEnergyConsumption", data);
  }
  
  uploadImage(data,energyConsumptionId) {
  	return http.postForm("/energyConsumption/uploadImage/"+energyConsumptionId, data);
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

export default new EnergyConsumptionService();
