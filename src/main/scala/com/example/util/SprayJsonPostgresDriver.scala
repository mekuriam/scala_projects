package com.example.util

/**
  * Created by mamekuri on 8/12/17.
  */
import com.github.tminglei.slickpg._

trait SprayJsonPostgresDriver extends ExPostgresProfile with PgSprayJsonSupport
  {

  override val pgjson = "jsonb"
  override val api = new API with SprayJsonImplicits {}
}

object MyPostgresDriver extends SprayJsonPostgresDriver