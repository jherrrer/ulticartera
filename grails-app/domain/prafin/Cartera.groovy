package prafin

class Cartera {
	Integer CC;
	static HasMany=[recibos:Recibo]
	Integer sale;
	Integer entra;
		static constraints = {
		}
		public Integer getCC(){
			return CC;
		}
		public Integer getsale(){
			return sale;
		}
		public Integer getentra(){
			return entra;
		}

}
