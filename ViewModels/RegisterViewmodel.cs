using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace at_cnet.ViewModels
{
    public class RegisterViewmodel
    {
        [Required]
        [EmailAddress]
        public string Email { get; set; }

        [Required]
        [DataType(DataType.Password)]
        public string Password { get; set; }

        [Compare("Password", ErrorMessage = "As senhas precisam coincidir!")]
        [DataType(DataType.Password)]
        [DisplayName("Confirmar Senha")]
        public string ConfirmSenha { get; set; }
    }
}
