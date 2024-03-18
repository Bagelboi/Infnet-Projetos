using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace tp3_cnet.Migrations
{
    /// <inheritdoc />
    public partial class v1 : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Computador",
                columns: table => new
                {
                    Id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Marca = table.Column<string>(type: "TEXT", nullable: false),
                    Processador = table.Column<string>(type: "TEXT", nullable: false),
                    PlacaMae = table.Column<string>(type: "TEXT", nullable: false),
                    Memoria = table.Column<string>(type: "TEXT", nullable: false),
                    HD = table.Column<string>(type: "TEXT", nullable: false),
                    NumeroPatrimonio = table.Column<string>(type: "TEXT", nullable: false),
                    DataCompra = table.Column<DateTime>(type: "TEXT", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Computador", x => x.Id);
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Computador");
        }
    }
}
