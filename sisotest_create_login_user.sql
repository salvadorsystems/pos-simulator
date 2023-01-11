--------------------------------------------------------------------------------------
-- siso_create_login_user.sql
--------------------------------------------------------------------------------------
-- Descripcion : Permite crear los usuarios para la Base de Datos del SISO/TEST
-- Creado      : 22-06-2017
-- Autor       : MARCO SALVADOR
-------------------------------------------------------------------------------------
-- Notas
--  1.-Reemplazar sisotest por el nombre de la Base de Datos a trabajar
-------------------------------------------------------------------------------------

USE [master]
GO

IF NOT EXISTS (SELECT * FROM sys.server_principals WHERE name = N'sisotest')
CREATE LOGIN [sisotest] WITH PASSWORD=N'password', DEFAULT_DATABASE=[SISOTEST.CMACICA], DEFAULT_LANGUAGE=[us_english], CHECK_EXPIRATION=OFF, CHECK_POLICY=OFF
GO

USE [SISOTEST.CMACICA]
GO
IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = N'sisotest')
CREATE USER [sisotest] FOR LOGIN [sisotest] WITH DEFAULT_SCHEMA=[dbo]
GO
GRANT CONNECT TO [sisotest] AS [dbo]
GO
GRANT DELETE TO [sisotest] AS [dbo]
GO
GRANT INSERT TO [sisotest] AS [dbo]
GO
GRANT SELECT TO [sisotest] AS [dbo]
GO
GRANT UPDATE TO [sisotest] AS [dbo]
GO

